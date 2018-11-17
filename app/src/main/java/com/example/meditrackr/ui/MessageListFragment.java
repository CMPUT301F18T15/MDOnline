/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 */
package com.example.meditrackr.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.MessageListAdapter;
import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.Comment;
import com.example.meditrackr.models.CommentList;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Profile;

/**
 * Created by Skryt on Nov 15, 2018
 */

public class MessageListFragment extends Fragment {
    private MessageListAdapter adapter;
    private Profile profile;
    private CommentList comments;
    private Patient patient;

    public static MessageListFragment newInstance(CommentList comments){
        MessageListFragment fragment = new MessageListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Messages", comments);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_message_list, container, false);

        final Button sendbutton = (Button) rootView.findViewById(R.id.button_chatbox_send);
        final EditText chatBox = (EditText) rootView.findViewById(R.id.edittext_chatbox);
        final RecyclerView messageList = (RecyclerView) rootView.findViewById(R.id.reyclerview_message_list);

        //comments = (CommentList) getArguments().getSerializable("Messages");
        profile = ProfileManager.getProfile();
        Log.d("WOOT", profile.getUsername());


        // need to fix this in the future, this is way too fucking hacky 
        if(profile.getisCareProvider()){
            Log.d("WOOT", "do i get here?");
            patient = ProfileManager.getCarePatient();
            comments = patient.getProblem(ProfileManager.getProblemIndex()).getComments();
        }else{
            patient = ProfileManager.getPatient();
            comments = patient.getProblem(ProfileManager.getProblemIndex()).getComments();
        }


        // initialize the messageList adapter
        messageList.setHasFixedSize(false);
        adapter = new MessageListAdapter(getContext(), comments);
        messageList.setAdapter(adapter);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        messageList.setLayoutManager(manager);
        messageList.setLayoutManager(manager);
        messageList.smoothScrollToPosition(comments.getSize());


        // on click listener for send button
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chatBox.getText().toString().trim().isEmpty()) {
                    Comment comment = new Comment(
                            chatBox.getText().toString(),
                            profile.getUsername()

                    );
                    comments.addComment(comment);
                    adapter.notifyDataSetChanged();
                    chatBox.setText(null);
                    //SaveLoadController.saveProfile(getContext(), profile);
                    ElasticSearchController.updateUser(patient);
                    messageList.smoothScrollToPosition(comments.getSize());
                }
                else{
                    Log.d("Messaging", "idiot user tried to input an empty string");
                    chatBox.setText(null);
                }
            }
        });

        return rootView;
    }
}

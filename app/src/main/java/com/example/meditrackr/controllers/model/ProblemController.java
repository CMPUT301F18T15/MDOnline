/*--------------------------------------------------------------------------
 * FILE: ProblemController.java
 *
 * PURPOSE: Manages adding, editing, and deleting problems and saving them
 *           both locally and on ElasticSearch.
 *
 *     Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 --------------------------------------------------------------------------*/

package com.example.meditrackr.controllers.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.utils.ThreadSave;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Problem;
import com.example.meditrackr.models.ProblemList;

import es.dmoral.toasty.Toasty;

/**
 * ProblemController
 *
 * Controls the adding of
 * a problem, if successful it
 * will save the problem both locally
 * and on ElasticSearch
 *
 * @author  Veronica Salm
 * @version 1.1 Nov 28, 2018
 */

// Controller class for problem objects
public class ProblemController {
    private static Patient patient = LazyLoadingManager.getPatient();


    /**
     * adds problem to database
     *
     * @param context   the context the controller will user
     * @param problem   the problem we will add to the database
     */
    // Add problem to problem list
    public static void addProblem(Context context, Problem problem) {
        // Get patient profile and problem
        patient.getProblems().addProblem(problem);

        // Save the problem both locally and elastic search
        ThreadSave.save(context, patient);
        Log.d("ProblemAdd", "Profile: " + patient.getUsername() + " Problems: " + patient.getProblems());

        // let the user know everything was successful
        Toasty.success(context, "Problem successfully added", Toast.LENGTH_SHORT).show();
    }

    // Delete a problem
    public static void deleteProblem(Context context, int index, ProblemList problems){
        problems.removeProblem(index);
        ThreadSave.save(context, patient);
    }

    // Edit a problem
    public static void editProblem(Context context, Problem problem, String title, String description, String date){
        problem.setTitle(title);
        problem.setDate(date);
        problem.setDescription(description);

        // Save problem into ES and memory
        ThreadSave.save(context, patient);

    }
}

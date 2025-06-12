package com.techenthusis.learningsession.presentation.activites.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.techenthusis.learningsession.presentation.viewmodel.CatFactViewModel
import com.techenthusis.learningsession.ui.theme.LearningSessionTheme

class MainActivity : ComponentActivity() {
    private val catFactViewModel by viewModels<CatFactViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val catFactState by catFactViewModel.catFact.collectAsState()
            val errorMessageState by catFactViewModel.errorMessage.collectAsState()
            val isLoading by catFactViewModel.isLoading.collectAsState()

            LearningSessionTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Cat Fact") })
                    },
                    content = { innerPadding ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator()
                            } else {
                                catFactState?.let { catFact ->
                                    Text(text = "Fact: ${catFact.fact}")
                                    Text(text = "Length: ${catFact.length}")
                                } ?: run {
                                    Text("Press the button to get a random cat fact!")
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = {
                                    catFactViewModel.fetchRandomCat()
                                }) {
                                Text("Get Random Cat Fact")
                            }
                        }

                        errorMessageState?.let { message ->
                            AlertDialog(
                                onDismissRequest = { catFactViewModel.errorMessageShown() },
                                title = { Text("Error") },
                                text = { Text(message) },
                                confirmButton = {
                                    TextButton(onClick = { catFactViewModel.errorMessageShown() }) {
                                        Text("Okay")
                                    }
                                }
                            )

                        }

                    })
            }
        }
    }

}

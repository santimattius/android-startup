package com.santimattius.android.startup

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.santimattius.android.feature.FeatureActivity
import com.santimattius.android.startup.service.CrashTrackerService
import com.santimattius.android.startup.service.ServiceExample
import com.santimattius.android.startup.ui.theme.AndroidStartupTheme
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {

    private val service by inject<ServiceExample>()
    private val crashTrackerService by inject<CrashTrackerService>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        service.execute()
        setContent {
            AndroidStartupTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(
                        title = "Android Startup with Hilt",
                        description = "Service initialized: ${crashTrackerService.isInitialized}"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(
    title: String,
    description: String,
) {

    val containerColor: Color = MaterialTheme.colorScheme.primary
    val titleContentColor: Color = MaterialTheme.colorScheme.onPrimary

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = containerColor,
                    titleContentColor = titleContentColor,
                    navigationIconContentColor = titleContentColor,
                    actionIconContentColor = titleContentColor,
                ),
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hello $title!",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium
                )
                val currentContext = LocalContext.current
                Button({
                    currentContext.startActivity(
                        Intent(
                            currentContext,
                            FeatureActivity::class.java
                        )
                    )
                }) {
                    Text(text = "Navigate to Feature")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidStartupTheme {
        Greeting("Android", "")
    }
}
package eu.tutorials.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tutorials.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember {
        mutableStateOf("")
    }

    var outputValue by remember {
        mutableStateOf("")
    }

    var inputUnit by remember {
        mutableStateOf("Meter")
    }

    var outputUnity by remember {
        mutableStateOf("Meters")
    }

    var iExpanded by remember {
        mutableStateOf(false)
    }
    var oExpended by remember {
        mutableStateOf(false)
    }
    val conversionFactor = remember {
        mutableStateOf(0.01)
    }
    val oConversionFactor = remember {
        mutableStateOf(1.00)
    }

    fun convertUnits(){
        // ?: -elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        //here all the ui el will be stacked below each other
        Text("Unit Converter",  style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = { // here goes what should happen when the output of our oulinefiled changes //
            inputValue = it
                convertUnits()
        },
            label = {
                Text(text = "Enter Value")
            } )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //input box
            Box {
                //input button
                Button(onClick = { iExpanded= true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters")},
                        onClick = {
                           iExpanded = false
                           inputUnit = "Centimeters"
                           conversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Milimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Milimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //output box
            Box {
                //output button
                Button(onClick = { oExpended = true }) {
                    Text(text = outputUnity)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpended, onDismissRequest = { oExpended = false}) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters")},
                        onClick = {
                            oExpended = false
                            outputUnity = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters")},
                        onClick = {
                            oExpended = false
                            outputUnity = "Meters"
                            oConversionFactor.value = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            oExpended = false
                            outputUnity = "Centimeters"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Milimeters")},
                        onClick = {
                            oExpended = false
                            outputUnity = "Milimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            //here all the ui el will be stacked next to each other

        }
        Spacer(modifier = Modifier.height(16.dp))
        //result text
        Text("Result: ${outputValue} ${outputUnity}",
            style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}


//@Composable
//fun unitConverterPreview(){
//    Column {
//        UnitConverter()
//        Row {
//            Text(text = "Result unitConverterPreview: ")
//        }
//    }
//}
//@Preview(showBackground = true)
//@Composable
//fun yourComposableNamePreview(){
//    unitConverterPreview()
//}
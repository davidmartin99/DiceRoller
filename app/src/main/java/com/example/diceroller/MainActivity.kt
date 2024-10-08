package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiceRollerScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DiceRollerScreen(modifier: Modifier = Modifier) {
    // Estados para los valores de los dados
    var dice1Value by remember { mutableStateOf(1) }
    var dice2Value by remember { mutableStateOf(1) }

    // Función para lanzar ambos dados
    fun rollDice() {
        dice1Value = Random.nextInt(1, 7)
        dice2Value = Random.nextInt(1, 7)
    }

    // Función para lanzar solo el dado 1
    fun rollDice1() {
        dice1Value = Random.nextInt(1, 7)
    }

    // Función para lanzar solo el dado 2
    fun rollDice2() {
        dice2Value = Random.nextInt(1, 7)
    }

    // Diseño de la interfaz
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF4CAF50)), // Color verde de fondo
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar las imágenes de los dados
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                painter = painterResource(id = getDiceImage(dice1Value)),
                contentDescription = "Dado 1",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = getDiceImage(dice2Value)),
                contentDescription = "Dado 2",
                modifier = Modifier.size(150.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para lanzar ambos dados
        Button(onClick = { rollDice() }) {
            Text("Echar el dado (ambos)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botones individuales para cada dado
        Row {
            Button(onClick = { rollDice1() }) {
                Text("Echar dado 1")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { rollDice2() }) {
                Text("Echar dado 2")
            }
        }
    }
}

// Función para obtener la imagen del dado correspondiente
fun getDiceImage(diceValue: Int): Int {
    return when (diceValue) {
        1 -> R.drawable.dice_1  // Cambia estos nombres si son diferentes
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6  // Dado por defecto
    }
}

@Preview(showBackground = true)
@Composable
fun DiceRollerScreenPreview() {
    DiceRollerTheme {
        DiceRollerScreen()
    }
}

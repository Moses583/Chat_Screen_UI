package com.ravemaster.statesjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravemaster.statesjetpackcompose.ui.theme.StatesJetpackComposeTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StatesJetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainComposer(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainComposer(modifier: Modifier = Modifier) {
    var input by remember {
        mutableStateOf("")
    }

    val myList = remember {
        mutableStateListOf<String>()
    }

    LazyColumnComposable(
        modifier = modifier,
        list = myList)

    TextFieldComposable(
        modifier = modifier,
        textValue = input,
        onValueChanged = { input = it },
        onAddClicked = {
            myList.add(input)
            input = ""
        }
    )
}


@Composable
fun LazyColumnComposable(
    modifier: Modifier = Modifier,
    list: List<String>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .padding(horizontal = 10.dp)
    ) {
        itemsIndexed(list){index, item ->
            Box(modifier = Modifier.fillMaxWidth()){
                if (index % 2 == 0){
                    LazyColumnItem(s = item, modifier = Modifier.align(Alignment.CenterEnd))
                } else {
                    LazyColumnItem(s = item, modifier = Modifier.align(Alignment.CenterStart))
                }
            }

        }
    }
}

@Composable
fun LazyColumnItem(s: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .widthIn(200.dp)
            .heightIn(70.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Cyan)
    ){
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(10.dp)
                .wrapContentSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = s,
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Blue
            )
        }

    }
}

@Composable
fun TextFieldComposable(
    modifier: Modifier = Modifier,
    textValue: String,
    onValueChanged: (String) -> Unit,
    onAddClicked: () -> Unit
) {

    Box(
        modifier = modifier.fillMaxSize().fillMaxHeight(0.1f),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = textValue,
                onValueChange = { onValueChanged(it)},
                modifier = modifier
                    .weight(0.8f)
                    .padding(start = 10.dp, bottom = 10.dp)
            )
            Spacer(modifier = modifier.width(10.dp))
            IconButton(
                modifier = modifier
                    .weight(0.2f)
                    .padding(end = 10.dp, bottom = 10.dp),
                colors = IconButtonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White,
                    disabledContentColor = Color.DarkGray,
                    disabledContainerColor = Color.LightGray
                ),
                onClick = onAddClicked
            ) {
                Icon(
                    imageVector = Icons.Rounded.Send,
                    contentDescription = "Send"
                )

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StatesJetpackComposeTheme {
    }
}
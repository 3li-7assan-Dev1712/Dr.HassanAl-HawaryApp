package com.example.hassanal_hawary.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hassanal_hawary.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
            .background(MaterialTheme.colorScheme.surface)
            .clip(RoundedCornerShape(size = 100.dp))
            .height(45.dp),
        verticalAlignment =  Alignment.CenterVertically
    ) {

        Image(
            imageVector = Icons.Default.Search,
            contentDescription = "Search"
        )

        Spacer(modifier = Modifier.width(16.dp))

        OutlinedTextField(
            placeholder = {
                Text("any")
            },
            value = "email", onValueChange = {
               // action
            }, modifier = Modifier.fillMaxWidth(), singleLine = true, leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email, contentDescription = "Email Section"
                )
            }, label = {
                Text(
                    text = LocalContext.current.getString(R.string.enter_email)
                )
            }

        )
    }

}

/*
This search bar view will let the users to do search in the app
to search for a specific content like lecture or video
 */
@Preview
@Composable
private fun SearchBarPrev() {

    SearchBar(modifier = Modifier.fillMaxSize())

}
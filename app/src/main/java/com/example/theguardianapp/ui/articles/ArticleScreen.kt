package com.example.theguardianapp.ui.articles

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.theguardianapp.model.Results
import com.example.theguardianapp.ui.theme.ContentTextPadding
import com.example.theguardianapp.ui.theme.HalfPadding
import com.example.theguardianapp.ui.theme.date

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ArticleScreen(
    item: Results
) {
    Scaffold(
        content = {
            ArticleScreenContent(
                item = item,
                modifier = Modifier.padding(it)
            )
        }
    )
}

@Composable
fun ArticleScreenContent(
    item: Results,
    modifier: Modifier
) {

    Column(
        modifier = modifier
            .padding(start = HalfPadding)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = item.webTitle,
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(HalfPadding))

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(HalfPadding),
            model = item.fields.thumbnail,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(HalfPadding))

        Text(
            modifier = Modifier
                .padding(start = ContentTextPadding, end = ContentTextPadding)
                .align(Alignment.CenterHorizontally),
            text = item.fields.bodyText,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(HalfPadding))

        Text(
            modifier = Modifier
                .align(Alignment.End)
                .padding(start = ContentTextPadding, end = ContentTextPadding)
                .align(Alignment.CenterHorizontally),
            text = item.webPublicationDate.date(),
            style = MaterialTheme.typography.bodySmall
        )
    }
}
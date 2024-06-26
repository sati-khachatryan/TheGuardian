package com.example.theguardianapp.ui.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.theguardianapp.model.Fields
import com.example.theguardianapp.model.Results
import com.example.theguardianapp.ui.theme.HalfPadding
import com.example.theguardianapp.ui.theme.NewsCardItemElevation
import com.example.theguardianapp.ui.theme.date

@Composable
fun NewsArticle(
    newsItem: Results,
    onItemClick: () -> Unit
) {
    Card(
        onClick = {
            onItemClick()
        },
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(NewsCardItemElevation)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(HalfPadding)
        ) {
            AsyncImage(
                model = newsItem.fields.thumbnail,
                modifier = Modifier
                    .height(128.dp)
                    .padding(end = HalfPadding)
                    .weight(1f),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )

            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = newsItem.webTitle,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                Text(
                    text = newsItem.webPublicationDate.date(),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewsArticleItem() {
    val article = Results(
        index = 1,
        webPublicationDate = "2024-05-20T09:20:08Z",
        webTitle = "London-listed Keywords Studios says it will accept £2bn offer",
        fields = Fields(
            bodyText = "",
            thumbnail = "https://media.guim.co.uk/e439308c7c1a276ea7245a840c74960680446655/0_87_4200_2522/500.jpg"
        )
    )
    NewsArticle(newsItem = article, {})
}
package up.trabalho01.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun clube() {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Column() {
            Text(text = "Clube", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Black
        )

        Card(modifier = Modifier.fillMaxWidth().height(300.dp)) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text( text = "Benefícios:", fontSize = 25.sp, modifier = Modifier.padding(15.dp))
                Text( text = "- R$ 0,01 desconto a cada R$ 1.000 na recarga de telefone.", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
                Text( text = "- 1 ponto a mais no sistema de pontuação a cada 100 pontos.", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
                Text( text = "- Seguro pix incluso", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
            }
        }

        Spacer(Modifier.padding(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {},
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.height(50.dp).width(250.dp)
            ) {
                Text(text = "Assinar", color = Color.White, fontSize = 20.sp)
            }
        }
    }
}
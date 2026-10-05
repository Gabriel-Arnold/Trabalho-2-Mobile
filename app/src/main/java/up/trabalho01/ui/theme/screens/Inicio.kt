package up.trabalho01.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Pix
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val BankBlue = Color(0xFF155EEF)
private val DarkBlue = Color(0xFF0B1F44)
private val Background = Color(0xFFF6F7F9)
private val TextSecondary = Color(0xFF6B7280)

@Composable
fun home() {

    val limit = 100f
    val used = 80f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 20.dp)
    ) {

        Spacer(modifier = Modifier.height(28.dp))

        // Cabeçalho
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(
                    text = "Agi&Otta",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBlue
                )

                Text(
                    text = "Bank",
                    fontSize = 14.sp,
                    color = TextSecondary
                )
            }

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(DarkBlue),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "G",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Saldo
        Text(
            text = "Saldo disponível",
            fontSize = 14.sp,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "R$ 08,32",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Ações rápidas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            QuickAction(
                icon = Icons.Default.Pix,
                title = "Pix"
            )

            QuickAction(
                icon = Icons.Default.ReceiptLong,
                title = "Pagar"
            )

            QuickAction(
                icon = Icons.Default.Add,
                title = "Depositar"
            )

            QuickAction(
                icon = Icons.Default.CreditCard,
                title = "Cartões"
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Clube
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(145.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = DarkBlue
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(
                        text = "Clube Agi&Otta",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Benefícios exclusivos para você.",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.75f)
                    )
                }

                Button(
                    onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = DarkBlue
                    ),
                    contentPadding = PaddingValues(
                        horizontal = 18.dp,
                        vertical = 4.dp
                    )
                ) {
                    Text(
                        text = "Conhecer clube",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Limite
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Limite do cartão",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF111827)
                    )

                    Icon(
                        imageVector = Icons.Default.CreditCard,
                        contentDescription = null,
                        tint = BankBlue
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

                Text(
                    text = "Disponível",
                    fontSize = 13.sp,
                    color = TextSecondary
                )

                Text(
                    text = "R$ %.2f".format(limit - used),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF111827)
                )

                Spacer(modifier = Modifier.height(18.dp))

                LinearProgressIndicator(
                    progress = {
                        if (limit > 0) used / limit else 0f
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    color = BankBlue,
                    trackColor = Color(0xFFE5E7EB)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "R$ %.2f usados".format(used),
                        fontSize = 12.sp,
                        color = TextSecondary
                    )

                    Text(
                        text = "Limite R$ %.2f".format(limit),
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}

@Composable
fun QuickAction(
    icon: ImageVector,
    title: String
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Surface(
            modifier = Modifier.size(58.dp),
            shape = RoundedCornerShape(18.dp),
            color = Color.White,
            shadowElevation = 1.dp
        ) {

            Box(
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    modifier = Modifier.size(25.dp),
                    tint = DarkBlue
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF374151)
        )
    }
}
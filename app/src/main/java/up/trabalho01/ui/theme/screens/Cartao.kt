package up.trabalho01.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Visibility
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
fun cartao() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 20.dp)
    ) {

        Spacer(modifier = Modifier.height(28.dp))

        // Título
        Text(
            text = "Cartões",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBlue
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Gerencie seus cartões e sua fatura.",
            fontSize = 14.sp,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Fatura atual
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

                    Column {

                        Text(
                            text = "Fatura atual",
                            fontSize = 14.sp,
                            color = TextSecondary
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "R$ 1.284,52",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF111827)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(BankBlue.copy(alpha = 0.10f)),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.ReceiptLong,
                            contentDescription = null,
                            tint = BankBlue
                        )
                    }
                }

                Spacer(modifier = Modifier.height(22.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {
                        Text(
                            text = "Fecha em",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )

                        Text(
                            text = "18 OUT",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {

                        Text(
                            text = "Vencimento",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )

                        Text(
                            text = "25 OUT",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BankBlue
                    )
                ) {

                    Text(
                        text = "Ver fatura",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Ações
        Text(
            text = "Acesso rápido",
            fontSize = 19.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF111827)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            CardAction(
                icon = Icons.Default.ReceiptLong,
                title = "Fatura"
            )

            CardAction(
                icon = Icons.Default.Lock,
                title = "Bloquear"
            )

            CardAction(
                icon = Icons.Default.Visibility,
                title = "Dados"
            )

            CardAction(
                icon = Icons.Default.MoreHoriz,
                title = "Mais"
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Cartões do usuário
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Meus cartões",
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF111827)
            )

            Text(
                text = "2 cartões",
                fontSize = 13.sp,
                color = TextSecondary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Cartão principal
        BankCard(
            cardName = "Agi&Otta Platinum",
            number = "••••  ••••  ••••  4821",
            holder = "GABRIEL",
            color = DarkBlue
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Segundo cartão
        BankCard(
            cardName = "Agi&Otta Virtual",
            number = "••••  ••••  ••••  7354",
            holder = "GABRIEL",
            color = BankBlue
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun BankCard(
    cardName: String,
    number: String,
    holder: String,
    color: Color
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = cardName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

                Icon(
                    imageVector = Icons.Default.CreditCard,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(27.dp)
                )
            }

            Text(
                text = number,
                fontSize = 19.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {

                Column {

                    Text(
                        text = "TITULAR",
                        fontSize = 9.sp,
                        color = Color.White.copy(alpha = 0.65f)
                    )

                    Text(
                        text = holder,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }

                Text(
                    text = "VISA",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun CardAction(
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
                    modifier = Modifier.size(24.dp),
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
package com.ediptan01.santiyeustasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SantiyeUstasi()
        }
    }
}

@Composable
fun SantiyeUstasi() {

    var ekran by remember { mutableStateOf("menu") }
    var para by remember { mutableStateOf(10000) }

    MaterialTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF101214)
        ) {

            when (ekran) {

                "menu" -> AnaMenu(
                    para = para,
                    basla = {
                        ekran = "sehir"
                    }
                )

                "sehir" -> SehirHaritasi(
                    para = para,
                    santiyeAc = {
                        ekran = "santiye"
                    },
                    geri = {
                        ekran = "menu"
                    }
                )

                "santiye" -> Santiye(
                    para = para,
                    tamamla = {
                        para += 35000
                    },
                    geri = {
                        ekran = "sehir"
                    }
                )
            }
        }
    }
}

@Composable
fun AnaMenu(
    para: Int,
    basla: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "ŞANTİYE USTASI",
            color = Color(0xFFFFB52E),
            fontSize = 38.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "İnşa et • Kazan • Büyüt",
            color = Color.LightGray,
            fontSize = 17.sp
        )

        Spacer(modifier = Modifier.height(35.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1C2025)
            ),
            shape = RoundedCornerShape(18.dp)
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "ŞİRKET KASASI",
                    color = Color.Gray,
                    fontSize = 13.sp
                )

                Text(
                    text = "%,d TL".format(para),
                    color = Color(0xFFFFB52E),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = basla,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(16.dp)
        ) {

            Text(
                text = "OYUNA BAŞLA",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SehirHaritasi(
    para: Int,
    santiyeAc: () -> Unit,
    geri: () -> Unit
) {

    var carX by remember { mutableStateOf(0.50f) }
    var carY by remember { mutableStateOf(0.72f) }

    var mesaj by remember {
        mutableStateOf("Şantiyeye git")
    }

    val santiyeX = 0.72f
    val santiyeY = 0.28f

    val santiyeYakin =
        abs(carX - santiyeX) < 0.10f &&
        abs(carY - santiyeY) < 0.10f

    LaunchedEffect(santiyeYakin) {
        if (santiyeYakin) {
            mesaj = "Şantiyeye ulaştın!"
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF75836B))
    ) {

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            val w = size.width
            val h = size.height

            /*
             * ZEMİN
             */

            drawRect(
                color = Color(0xFF75836B),
                size = size
            )

            /*
             * ANA YOLLAR
             */

            drawRect(
                color = Color(0xFF30343A),
                topLeft = Offset(0f, h * 0.42f),
                size = Size(w, h * 0.18f)
            )

            drawRect(
                color = Color(0xFF30343A),
                topLeft = Offset(w * 0.42f, 0f),
                size = Size(w * 0.18f, h)
            )

            /*
             * YOL ÇİZGİLERİ
             */

            var x = 0f

            while (x < w) {

                drawRect(
                    color = Color(0xFFFFD85A),
                    topLeft = Offset(x, h * 0.505f),
                    size = Size(45f, 6f)
                )

                x += 85f
            }

            var y = 0f

            while (y < h) {

                drawRect(
                    color = Color(0xFFFFD85A),
                    topLeft = Offset(w * 0.505f, y),
                    size = Size(6f, 45f)
                )

                y += 85f
            }

            /*
             * BİNALAR
             */

            bina(
                this,
                w * 0.08f,
                h * 0.10f,
                150f,
                110f,
                Color(0xFF9C9C9C)
            )

            bina(
                this,
                w * 0.08f,
                h * 0.65f,
                170f,
                100f,
                Color(0xFFB7A58A)
            )

            bina(
                this,
                w * 0.67f,
                h * 0.08f,
                150f,
                110f,
                Color(0xFF8B98A8)
            )

            bina(
                this,
                w * 0.68f,
                h * 0.67f,
                160f,
                110f,
                Color(0xFF9B8C7A)
            )

            /*
             * SANAYİ
             */

            drawRect(
                color = Color(0xFF52575B),
                topLeft = Offset(w * 0.04f, h * 0.36f),
                size = Size(w * 0.28f, h * 0.12f)
            )

            /*
             * BENZİNLİK
             */

            drawRect(
                color = Color(0xFFE7E7E7),
                topLeft = Offset(w * 0.67f, h * 0.37f),
                size = Size(130f, 80f)
            )

            drawRect(
                color = Color(0xFFD73535),
                topLeft = Offset(w * 0.67f, h * 0.37f),
                size = Size(130f, 15f)
            )

            /*
             * ŞANTİYE
             */

            val sx = w * santiyeX
            val sy = h * santiyeY

            drawRect(
                color = Color(0xFFB78645),
                topLeft = Offset(sx - 75f, sy - 55f),
                size = Size(150f, 110f)
            )

            drawRect(
                color = Color(0xFFE3C05B),
                topLeft = Offset(sx - 60f, sy - 40f),
                size = Size(120f, 80f),
                style = Stroke(width = 5f)
            )

            /*
             * ŞANTİYE UYARI ÇEMBERİ
             */

            drawCircle(
                color = Color(0xFFFFB52E),
                radius = 48f,
                center = Offset(sx, sy),
                style = Stroke(width = 5f)
            )

            /*
             * ARAÇ
             */

            val cx = w * carX
            val cy = h * carY

            drawRoundRect(
                color = Color(0xFFE0A52A),
                topLeft = Offset(cx - 32f, cy - 20f),
                size = Size(64f, 40f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(10f, 10f)
            )

            drawCircle(
                color = Color.Black,
                radius = 8f,
                center = Offset(cx - 20f, cy + 20f)
            )

            drawCircle(
                color = Color.Black,
                radius = 8f,
                center = Offset(cx + 20f, cy + 20f)
            )

            /*
             * HEDEF ÇİZGİSİ
             */

            if (!santiyeYakin) {

                drawLine(
                    color = Color(0xFFFFC107),
                    start = Offset(cx, cy),
                    end = Offset(sx, sy),
                    strokeWidth = 5f
                )
            }
        }

        /*
         * ÜST BİLGİ
         */

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xE6202328)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "ŞEHİR",
                        color = Color.White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = mesaj,
                        color = Color(0xFFFFB52E),
                        fontSize = 13.sp
                    )
                }

                Text(
                    text = "%,d TL".format(para),
                    color = Color(0xFFFFB52E),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        /*
         * ŞANTİYE BUTONU
         */

        if (santiyeYakin) {

            Button(
                onClick = santiyeAc,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 105.dp),
                shape = RoundedCornerShape(14.dp)
            ) {

                Text("ŞANTİYEYE GİR")
            }
        }

        /*
         * YÖN KONTROLLERİ
         */

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            KontrolButonu("▲") {

                carY = (carY - 0.05f).coerceAtLeast(0.08f)
            }

            Row {

                KontrolButonu("◀") {

                    carX = (carX - 0.05f).coerceAtLeast(0.05f)
                }

                Spacer(modifier = Modifier.width(8.dp))

                KontrolButonu("●") {
                    // Araç durur.
                }

                Spacer(modifier = Modifier.width(8.dp))

                KontrolButonu("▶") {

                    carX = (carX + 0.05f).coerceAtMost(0.95f)
                }
            }

            KontrolButonu("▼") {

                carY = (carY + 0.05f).coerceAtMost(0.92f)
            }
        }

        /*
         * ANA MENÜ
         */

        OutlinedButton(
            onClick = geri,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .navigationBarsPadding()
                .padding(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.White
            )
        ) {
            Text("MENÜ")
        }
    }
}

fun bina(
    scope: androidx.compose.ui.graphics.drawscope.DrawScope,
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    color: Color
) {

    scope.drawRect(
        color = color,
        topLeft = Offset(x, y),
        size = Size(width, height)
    )

    /*
     * Pencereler
     */

    val pencere = Color(0xFFBFD5E3)

    for (i in 0..2) {

        for (j in 0..1) {

            scope.drawRect(
                color = pencere,
                topLeft = Offset(
                    x + 20f + i * 40f,
                    y + 20f + j * 40f
                ),
                size = Size(20f, 22f)
            )
        }
    }
}

@Composable
fun KontrolButonu(
    text: String,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        modifier = Modifier.size(62.dp),
        shape = RoundedCornerShape(14.dp),
        contentPadding = PaddingValues(0.dp)
    ) {

        Text(
            text = text,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Santiye(
    para: Int,
    tamamla: () -> Unit,
    geri: () -> Unit
) {

    var tamamlandi by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(20.dp)
    ) {

        Text(
            text = "ŞANTİYE",
            color = Color(0xFFFFB52E),
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1C2025)
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "80 m² ALÇIPAN TAVAN",
                    color = Color.White,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "İş bedeli: 35.000 TL",
                    color = Color.LightGray
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        if (!tamamlandi) {

            Button(
                onClick = {
                    tamamlandi = true
                    tamamla()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {

                Text(
                    text = "İŞİ TAMAMLA",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        } else {

            Text(
                text = "✓ İŞ TAMAMLANDI",
                color = Color(0xFF62D477),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "35.000 TL kazandın!",
                color = Color.White,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Kasa: %,d TL".format(para),
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = geri,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("ŞEHRE DÖN")
        }
    }
}

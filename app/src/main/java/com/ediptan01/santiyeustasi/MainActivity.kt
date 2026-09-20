package com.ediptan01.santiyeustasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SantiyeUstasi()
        }
    }
}

@Composable
fun SantiyeUstasi() {

    var ekran by remember { mutableStateOf("menu") }
    var para by remember { mutableStateOf(10000) }
    var gorevTamamlandi by remember { mutableStateOf(false) }

    MaterialTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF101214)
        ) {

            when (ekran) {

                "menu" -> AnaMenu(
                    para = para,
                    oyunaBasla = {
                        ekran = "sehir"
                    }
                )

                "sehir" -> Sehir(
                    para = para,
                    geri = {
                        ekran = "menu"
                    },
                    santiye = {
                        ekran = "santiye"
                    }
                )

                "santiye" -> Santiye(
                    para = para,
                    tamamlandi = gorevTamamlandi,
                    geri = {
                        ekran = "sehir"
                    },
                    isiTamamla = {
                        if (!gorevTamamlandi) {
                            para += 35000
                            gorevTamamlandi = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun AnaMenu(
    para: Int,
    oyunaBasla: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "ŞANTİYE USTASI",
            color = Color(0xFFFFB300),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Kendi inşaat şirketini kur.",
            color = Color.LightGray,
            fontSize = 17.sp
        )

        Spacer(modifier = Modifier.height(35.dp))

        BilgiKutusu(
            baslik = "Şirket Kasası",
            deger = "$para TL"
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = oyunaBasla,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(
                text = "OYUNA BAŞLA",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ŞİRKETİM")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ARAÇLARIM")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("AYARLAR")
        }
    }
}

@Composable
fun Sehir(
    para: Int,
    geri: () -> Unit,
    santiye: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "ŞEHİR",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "$para TL",
                color = Color(0xFFFFB300),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        SehirKart(
            baslik = "🏗️ ŞANTİYE",
            aciklama = "Yeni bir inşaat işi var.",
            buton = "ŞANTİYEYE GİT",
            tikla = santiye
        )

        Spacer(modifier = Modifier.height(15.dp))

        SehirKart(
            baslik = "🔧 SANAYİ",
            aciklama = "Araçlarını burada geliştirebilirsin.",
            buton = "YAKINDA",
            tikla = {}
        )

        Spacer(modifier = Modifier.height(15.dp))

        SehirKart(
            baslik = "🏪 YAPI MARKET",
            aciklama = "İnşaat malzemeleri satın al.",
            buton = "YAKINDA",
            tikla = {}
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = geri,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ANA MENÜ")
        }
    }
}

@Composable
fun Santiye(
    para: Int,
    tamamlandi: Boolean,
    geri: () -> Unit,
    isiTamamla: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "🏗️ ŞANTİYE",
            color = Color(0xFFFFB300),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(25.dp))

        BilgiKutusu(
            baslik = "Aktif İş",
            deger = "80 m² Alçıpan Tavan"
        )

        Spacer(modifier = Modifier.height(12.dp))

        BilgiKutusu(
            baslik = "İş Bedeli",
            deger = "35.000 TL"
        )

        Spacer(modifier = Modifier.height(25.dp))

        if (!tamamlandi) {

            Button(
                onClick = isiTamamla,
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
                color = Color(0xFF66BB6A),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "35.000 TL kazandın!",
                color = Color.White,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Kasa: $para TL",
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = geri,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ŞEHRE DÖN")
        }
    }
}

@Composable
fun BilgiKutusu(
    baslik: String,
    deger: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1B1F23)
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = baslik,
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = deger,
                color = Color.White,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SehirKart(
    baslik: String,
    aciklama: String,
    buton: String,
    tikla: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1B1F23)
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = baslik,
                color = Color.White,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = aciklama,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = tikla
            ) {
                Text(buton)
            }
        }
    }
}

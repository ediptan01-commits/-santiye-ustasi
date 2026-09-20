package com.ediptan01.santiyeustasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    var gorevTamamlandi by remember { mutableStateOf(false) }

    MaterialTheme {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0D0F12)),
            color = Color(0xFF0D0F12)
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
                    santiye = {
                        ekran = "santiye"
                    },
                    geri = {
                        ekran = "menu"
                    }
                )

                "santiye" -> Santiye(
                    para = para,
                    tamamlandi = gorevTamamlandi,
                    isiTamamla = {
                        if (!gorevTamamlandi) {
                            para += 35000
                            gorevTamamlandi = true
                        }
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
    oyunaBasla: () -> Unit
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
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "İnşa et • Kazan • Büyüt",
            color = Color(0xFFB5B8BE),
            fontSize = 17.sp
        )

        Spacer(modifier = Modifier.height(35.dp))

        BilgiKutusu(
            baslik = "ŞİRKET KASASI",
            deger = "%,d TL".format(para)
        )

        Spacer(modifier = Modifier.height(25.dp))

        BuyukButon(
            text = "OYUNA BAŞLA",
            onClick = oyunaBasla
        )

        Spacer(modifier = Modifier.height(12.dp))

        NormalButon("ŞİRKETİM")
        NormalButon("ARAÇLARIM")
        NormalButon("AYARLAR")
    }
}

@Composable
fun Sehir(
    para: Int,
    santiye: () -> Unit,
    geri: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 18.dp)
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Text(
                    text = "ŞEHİR",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = "Bugün yeni işler var",
                    color = Color(0xFF92969D),
                    fontSize = 14.sp
                )
            }

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF20242A)
                )
            ) {

                Text(
                    text = "%,d TL".format(para),
                    modifier = Modifier.padding(
                        horizontal = 14.dp,
                        vertical = 10.dp
                    ),
                    color = Color(0xFFFFB52E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        MekanKart(
            emoji = "🏗️",
            baslik = "ŞANTİYE",
            aciklama = "Yeni bir inşaat işi seni bekliyor.",
            buton = "ŞANTİYEYE GİT",
            aktif = true,
            onClick = santiye
        )

        Spacer(modifier = Modifier.height(12.dp))

        MekanKart(
            emoji = "🔧",
            baslik = "SANAYİ",
            aciklama = "Araçlarını tamir et ve geliştir.",
            buton = "YAKINDA",
            aktif = false,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(12.dp))

        MekanKart(
            emoji = "🏪",
            baslik = "YAPI MARKET",
            aciklama = "İnşaat malzemelerini satın al.",
            buton = "YAKINDA",
            aktif = false,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(12.dp))

        MekanKart(
            emoji = "⛽",
            baslik = "BENZİNLİK",
            aciklama = "Araçlarının yakıtını doldur.",
            buton = "YAKINDA",
            aktif = false,
            onClick = {}
        )

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = geri,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("ANA MENÜ")
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun MekanKart(
    emoji: String,
    baslik: String,
    aciklama: String,
    buton: String,
    aktif: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF191D22)
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = emoji,
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {

                    Text(
                        text = baslik,
                        color = Color.White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = aciklama,
                        color = Color(0xFFA7ABB2),
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onClick,
                enabled = aktif,
                modifier = Modifier.height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(buton)
            }
        }
    }
}

@Composable
fun Santiye(
    para: Int,
    tamamlandi: Boolean,
    isiTamamla: () -> Unit,
    geri: () -> Unit
) {

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
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(20.dp))

        BilgiKutusu(
            baslik = "AKTİF İŞ",
            deger = "80 m² Alçıpan Tavan"
        )

        Spacer(modifier = Modifier.height(12.dp))

        BilgiKutusu(
            baslik = "İŞ BEDELİ",
            deger = "35.000 TL"
        )

        Spacer(modifier = Modifier.height(25.dp))

        if (!tamamlandi) {

            BuyukButon(
                text = "İŞİ TAMAMLA",
                onClick = isiTamamla
            )

        } else {

            Text(
                text = "✓ İŞ TAMAMLANDI",
                color = Color(0xFF62D477),
                fontSize = 22.sp,
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
            color = Color(0xFFB5B8BE)
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

@Composable
fun BilgiKutusu(
    baslik: String,
    deger: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF191D22)
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = baslik,
                color = Color(0xFF8F949B),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
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
fun BuyukButon(
    text: String,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Text(
            text = text,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun NormalButon(text: String) {

    OutlinedButton(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text)
    }

    Spacer(modifier = Modifier.height(7.dp))
}

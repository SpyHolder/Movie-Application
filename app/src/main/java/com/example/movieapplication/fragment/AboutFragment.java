package com.example.movieapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapplication.R;

public class AboutFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // I Have Secret !)
        // "}"
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);







































































        String MyName = "Hallo semua, perkenalkan saya Mohd.Ikhsan Sadillah atau kalian bisa memanggil aku ikhsan";

        String AboutApplication = "Kali ini saya ingin memperkenalkan aplikasi saya yaitu 'Movie Application'. Terus fungsinya apa?" +
                "Apakah sama halnya dengan website movie yang bisa kita download filmnya? Eits ini sama sekali berbeda, ya sebenarnya ada yang" +
                "sama sih. Tapi di aplikasi ini kalian bisa melihat seluruh informasi tentang sebuah film. Entah itu sutradaranya atau" +
                "aktor yang memerankan film tersebut. Aplikasi ini dibuat pada tahun 2023";

        String FiturApplication = "Dan juga aplikasi ini terdapat fitur-fitur yang menarik yaitu kalian para user bisa berkomentar pada " +
                "film yang mungkin kalian sudah nonton, fitur lainya yaitu adanya GenreFilter yang dimana kalian dapat dengan mudah" +
                "menemukan sebuah film hanya dengan mencari sebuah genre kesukaan kalian, dan tidak sampai disitu fitur selanjutnya ialah" +
                "adanya 'Edit Profile'. disini kalian bisa mengedit profil kalian dan juga disediakan 6 avatar yang SANGAT-SANGAT WIBU" +
                "jadi untuk para WIBU ni mungkin cocok untuk kalian :)";

        String Kekurangan = "Aplikasi mungkin ada banyak kekurangan, ya mungkin karna saya baru mencoba membuatnya sih :)." +
                "Jadi kalau misalnya ada terdapat sebuah bug ya mohon dimaklumin lah ya. Dan juga kekurangan lainnya ialah server API" +
                "yang mungkin tidak mendukung, kadang API nya mati, kadang tak bisa di akses. Kekurangan ini disebabkan karna saya" +
                "menggunakan hostingan yang gratisan. Sebenarnya hostingan ini untuk hosting web sih tapi saya jadikan hosting API aja :)." +
                "Jadi jika ada kendala dengan data yang ditampilakn saya dengan berat hati ingin minta maaf karna tidak dapat menyediakan" +
                "API yang lebih bagus, tapi kalau kalian ingin membantu saya untuk mendapatkan server API yang bagus SABILAH ;)." +
                "Hubungi aja Email saya sapigamimg@gmail.com, kalian juga bisa kok kasi tau terdapat BUG apa aja pada aplikasi ini :)";

        String Implementasi = "Bahasa pemograman yang saya gunakan untuk membuat aplikasi ini ya itu JAVA tapi kalau API nya pakai PHP" +
                "Waktu untuk menyelesaikan aplikasi ini mungkin sekitar SATU SETENGAH BULAN. Tampat saya hosting API itu pakai https://www.000webhost.com";

        String SpecialThanks = "Saya sangat berterima kasih untuk kalian semua yang udah mau mecoba aplikasi ini dan saya ucapkan juga " +
                "terima kasih untuk teman saya FARHAN karna beliau sudah membantu saya mulai dari membantu saya mendesain aplikasi ini" +
                "sampai menjadi bahan percobaan aplikasi :)";

        String TujuanDevelper = "Tujuan saya membuat aplikasi ini mungkin untuk menambah-nambah portofolio saya yang mungkin berguna" +
                "untuk masa yang akan mendatang dan juga agar saya bisa lebih memahami bahasa pemograman java lebih jauh";

        String CodeSource = "Kalau kalian ingin mendapatkan code source aplikasi ini kalian dapat melihatnya di github saya" +
                "https://github.com/SpyHolder/Movie-Application";
    }
}
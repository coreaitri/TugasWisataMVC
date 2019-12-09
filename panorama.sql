-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 09 Des 2019 pada 07.59
-- Versi server: 10.1.38-MariaDB
-- Versi PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `panorama`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `galery`
--

CREATE TABLE `galery` (
  `id` int(11) NOT NULL,
  `panorama` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `deskripsi` text NOT NULL,
  `lokasi` text NOT NULL,
  `image` text NOT NULL,
  `latitude` varchar(191) NOT NULL,
  `longitude` varchar(191) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `galery`
--

INSERT INTO `galery` (`id`, `panorama`, `city`, `deskripsi`, `lokasi`, `image`, `latitude`, `longitude`) VALUES
(1, 'Candi Borobudur', 'Yogyakarta', 'Candi Borobudur adalah candi Buddha terbesar di dunia. Dibangun pada abad ke-9, Candi Borobudur sekarang menjadi magnet yang mampu menarik jutaan wisatawan setiap tahun. Temukan fakta menarik, sejarah, dan gambar Candi Borobudur di sini. Udara begitu segar, bintang bertaburan di langit fajar. Suasana di Candi Borobudur terasa begitu hening dan damai. Tak lama lagi, sinar keemasan mentari pagi akan menerpa Candi Borobudur.\r\n', 'jog', 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS1rIuAV2bUlgJm8muKtfwH-EY152-90qx-sfGgzQnRHkCcb7wo', '-7.8032245', '110.2347565'),
(2, 'pulau dewata', 'bali', 'Sebuah surga kecil dengan berbagai keindahan dan keunikan, sehingga menjadi destinasi wisata dunia, tempat-tempat indahnya dijadikan agenda kunjungan dalam perjalanan tour. Dataran tersebut bernama pulau Bali juga dikenal dengan nama Pulau Dewata, nama ini muncul mungkin karena diangap agama Hindu tersebut memiliki banyak Dewa. Pulau Dewata ini terdiri dari 8 buah kabupaten  dan 1 kota madya, dengan luas 5.636,66 km2 atau sekitar 0,29% dari luas Indonesia secara keseluruhan. Gunung tertinggi adalah Gunung Agung dengan ketinggian 3.148 meter. ', 'bali', 'https://img.jakpost.net/c/2019/03/19/2019_03_19_67991_1552969698._large.jpg', '-8.4556974', '114.5110291'),
(3, 'bogor kebun raya', 'bogor', 'Kalau di Bogor ada bangunan begitu ikonis yakni kebun raya Bogor, menjadi salah satu kawasan wisata menyajikan berbagai panorama serta terselip history Bogor. Terdapat banyak peninggalan sejarah dan menjadi salah satu saksi bisu negara Indonesia dalam memperjuangkan sekaligus mempertahankan kemerdekaan. Keindahan alam dimiliki landmark Bogor ini memang belum ada duanya, meskipun mungkin tiap-tiap daerah di Indonesia memiliki kebun raya, tetap saja belum mampu menandingi dalam segala hal. Sebab wisatawan dapat menyaksikan perpaduan sempurna panorama dan sisi sejarah Indonesia sekaligus. Tak sampai disitu saja, dijamin deh sobat bakal mendapati banyak hal-hal menarik di sini. Lantaran wisata KRB (kebun raya Bogor) memiliki banyak sekali spot atau tempat-tempat keren memiliki penuh sejarah.', 'bog', 'https://www.tripjalanjalan.com/wp-content/uploads/2018/01/kebun-raya-bogor.jpg', '-6.5976289', '106.7973811');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`) VALUES
(1, 'feny', 'feny@gmail.com', 'feny'),
(2, 'nur', 'nur@gmail.com', 'nur'),
(3, 'siap', 'saip@gmail.com', '12345678');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `galery`
--
ALTER TABLE `galery`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `galery`
--
ALTER TABLE `galery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

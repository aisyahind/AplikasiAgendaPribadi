/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.agenda; 
import java.util.ArrayList;
import java.util.List;
import java.io.*;
/**
 *
 * @author USER
 */
// Kelas Controller: Menangani Logika Bisnis (CRUD, Export, Import, Cari).
public class AgendaController {
    
    private final List<agenda> daftarAgenda;

    // Constructor: Inisialisasi list penyimpanan data agenda.
    public AgendaController() {
        daftarAgenda = new ArrayList<>();
    }
    
    // C (Create/Tambah)
    // Tambah agenda baru ke list. Lakukan validasi input wajib.
    public void tambahAgenda(String judul, String tanggal, String waktu, String kategori, String deskripsi) {
        if (judul.isEmpty() || tanggal.isEmpty() || waktu.isEmpty() || kategori.isEmpty()) {
            throw new IllegalArgumentException("Judul, Tanggal, Waktu, dan Kategori tidak boleh kosong.");
        }
        // Membuat objek baru, ID otomatis di-generate di Model
        daftarAgenda.add(new agenda(judul, tanggal, waktu, kategori, deskripsi)); 
    }
    
    // R (Read/Ambil)
    // Ambil semua daftar agenda
    public List<agenda> getSemuaAgenda() {
        return daftarAgenda;
    }
    
    // Cari agenda berdasarkan ID unik
    public agenda getAgendaById(int id) {
        for (agenda a : daftarAgenda) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    // (Update/Edit)
    // Edit data agenda berdasarkan ID. Lakukan validasi input.
    public boolean editAgenda(int idToEdit, String newJudul, String newTanggal, String newWaktu, String newKategori, String newDeskripsi) {
        agenda agendaToEdit = getAgendaById(idToEdit);
        
        if (agendaToEdit != null) {
             if (newJudul.isEmpty() || newTanggal.isEmpty() || newWaktu.isEmpty() || newKategori.isEmpty()) {
                throw new IllegalArgumentException("Semua field (kecuali Deskripsi) harus diisi.");
            }
            // Menggunakan Setter dari Model
            agendaToEdit.setJudul(newJudul);
            agendaToEdit.setTanggal(newTanggal);
            agendaToEdit.setWaktu(newWaktu);
            agendaToEdit.setKategori(newKategori); 
            agendaToEdit.setDeskripsi(newDeskripsi);
            return true;
        }
        return false;
    }

    // (Delete/Hapus)
    // Hapus agenda dari list berdasarkan ID. 
    public boolean hapusAgenda(int idToDelete) {
        return daftarAgenda.removeIf(a -> a.getId() == idToDelete);
    }
    
    // Fungsionalitas ekstra
    // Export semua data agenda ke file CSV.a
    public void exportToCSV(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Tulis Header
            writer.write("ID,Judul,Tanggal,Waktu,Kategori,Deskripsi\n");
            
            for (agenda a : daftarAgenda) {
                // Tulis data, menangani kutip ganda untuk CSV
                String line = String.format("%d,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"\n", 
                                            a.getId(), 
                                            a.getJudul().replace("\"", "\"\""), 
                                            a.getTanggal().replace("\"", "\"\""),
                                            a.getWaktu().replace("\"", "\"\""),
                                            a.getKategori().replace("\"", "\"\""),
                                            a.getDeskripsi().replace("\"", "\"\""));
                writer.write(line);
            }
        }
    }
    
    // Import data dari file CSV dan perbarui list.
    public void importFromCSV(String filePath) throws IOException {
        daftarAgenda.clear(); // Hapus data lama
        int maxId = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // Lewati header
            String line;
            
            while ((line = reader.readLine()) != null) {
                // Regex untuk split CSV yang mendukung field diapit kutip ganda
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                
                if (parts.length == 6) {
                    try {
                        // Hilangkan kutip ganda dan spasi sisa
                        int id = Integer.parseInt(parts[0].trim().replace("\"", ""));
                        String judul = parts[1].trim().replace("\"", "");
                        String tanggal = parts[2].trim().replace("\"", "");
                        String waktu = parts[3].trim().replace("\"", "");
                        String kategori = parts[4].trim().replace("\"", "");
                        String deskripsi = parts[5].trim().replace("\"", "");

                        // Tambahkan ke daftar
                        daftarAgenda.add(new agenda(judul, tanggal, waktu, kategori, deskripsi));
                        if (id > maxId) maxId = id;
                        
                    } catch (NumberFormatException e) {
                        System.err.println("Gagal parsing data dari CSV: " + e.getMessage());
                    }
                }
            }
        }
        
        // Reset ID statis (nextId) agar penambahan data baru tidak bentrok.
        agenda.setNextId(maxId + 1); 
    }

    // Cari agenda berdasarkan kata kunci pada Judul.
public List<agenda> cariAgenda(String keyword) {
    List<agenda> hasil = new ArrayList<>();

    if (keyword == null || keyword.trim().isEmpty()) {
        return hasil;
    }

    keyword = keyword.toLowerCase();

    for (agenda a : daftarAgenda) {
        if (a.getJudul().toLowerCase().contains(keyword)) {
            hasil.add(a);
        }
    }
    return hasil;
}

    public Iterable<agenda> cariAgenda(Object keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
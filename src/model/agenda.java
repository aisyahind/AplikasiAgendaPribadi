/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class agenda {
    
// Static field untuk ID otomatis (digunakan di Controller)
    private static int nextId = 1; 

    // Atribut (Properties)
    private final int id; 
    private String judul;
    private String tanggal; // Disimpan sebagai String (YYYY-MM-DD)
    private String waktu;
    private String kategori; 
    private String deskripsi;

    // --- CONSTRUCTOR ---
    /**
     * Constructor untuk membuat objek agenda baru (digunakan saat Tambah/Import).
     */
    public agenda(String judul, String tanggal, String waktu, String kategori, String deskripsi) {
        this.id = nextId++; 
        this.judul = judul;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
    }

    // --- GETTER (Accessor Methods) ---
    public int getId() { 
        return id; 
    }
    
    public String getJudul() { 
        return judul; 
    }
    
    public String getTanggal() { 
        return tanggal; 
    }
    
    public String getWaktu() { 
        return waktu; 
    }
    
    public String getKategori() { 
        return kategori; 
    }
    
    public String getDeskripsi() { 
        return deskripsi; 
    }

    // --- SETTER (Mutator Methods) ---
    public void setJudul(String judul) { 
        this.judul = judul; 
    }
    
    public void setTanggal(String tanggal) { 
        this.tanggal = tanggal; 
    }
    
    public void setWaktu(String waktu) { 
        this.waktu = waktu; 
    }
    
    public void setKategori(String kategori) { 
        this.kategori = kategori; 
    }
    
    public void setDeskripsi(String deskripsi) { 
        this.deskripsi = deskripsi; 
    }

    // Method statis untuk mengatur ID setelah operasi Import
    public static void setNextId(int nextId) {
        agenda.nextId = nextId;
    }
}
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
   private static int nextId = 1; 

    private int id; 
    private String judul;
    private String tanggal; // Menggunakan String untuk kemudahan
    private String waktu;
    private String deskripsi;

    // Constructor untuk membuat objek Agenda baru
    public agenda(String judul, String tanggal, String waktu, String deskripsi) {
        this.id = nextId++;
        this.judul = judul;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.deskripsi = deskripsi;
    }

    // Getter (Accessors)
    public int getId() { return id; }
    public String getJudul() { return judul; }
    public String getTanggal() { return tanggal; }
    public String getWaktu() { return waktu; }
    public String getDeskripsi() { return deskripsi; }
    
    // Setter (Mutators) - Penting untuk operasi Edit
    public void setJudul(String judul) { this.judul = judul; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public void setWaktu(String waktu) { this.waktu = waktu; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    // Override toString (Berguna untuk JList atau debugging)
    @Override
    public String toString() {
        return id + " | " + judul + " - " + tanggal;
    } 
}

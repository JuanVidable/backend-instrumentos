package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
public class Usuario extends Base{
    private String nombreUsuario;
    private String claveEncriptada;
    private String rol;

    public void setClave(String clave) {
        try {
            // Selecciona el algoritmo de encriptación (por ejemplo, MD5)
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Convierte la contraseña en bytes
            byte[] messageDigest = md.digest(clave.getBytes());
            // Convierte el array de bytes a una representación hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            // Establece la contraseña encriptada
            this.claveEncriptada = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }
}

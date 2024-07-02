
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h

in al, 20; Zahl eins einelsen
mov dl, al; Zahl eins in dl speichern

in al, 21; Zahl zwei einlesen
mov dh, al; Zahl zwei nach dh verschieben

in al, 22; Operation auslesen

cmp al, 1   ; wenn 1 addieren
je addieren                  

cmp al, 2   ; wenn 2 subtrahieren
je subtrahieren                    

cmp al, 4   ; wenn 4 multiplizieren
je multiplizieren

cmp al, 8   ;wenn 8 Dividieren
je dividieren
ret
             

addieren:
    add dl, dh  ;ausgabe in DL  
    mov al, dl 
    jmp ausgeben

subtrahieren:
    sub dl, dh  ;ausgabe in dl 
    mov al, dl
    jmp ausgeben

multiplizieren:
    mov al, dl
    mul dh  ;ausgabe in ax
    jmp ausgeben

dividieren:
    mov al, dl
    div dh  ;ausgabe in ax
    jmp ausgeben
    
 

 
ausgeben:                 
    out 23, ax
    





ret





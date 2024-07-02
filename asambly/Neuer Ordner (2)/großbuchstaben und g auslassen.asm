; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h

mov cx, 256d
mov ah, 2d

loop:
     dec cx         ;zaehler decrementieren
     mov dx, 255d   
     sub dx, cx     ;auszugendes Zeichen ausrechenen: 255 - dx
     cmp dx, 65     
     jl loop    ;wenn dx kleiner als 65 ist wieder in die schleife
     cmp dx, 91     
     je stop       ;wenn dx gleich 91, also Z ist stopp
     cmp dx, 90
     jg loop    ;wenn dx kleiner als 90 ist dx ausgeben
     cmp dx, 71
     je setStar     ;wenn dx = '*' s
     jmp print
     jmp loop
     
setStar:
    mov dx,'*'
    jmp print
    
print:
    int 21h
    jmp loop
                  
stop:
    ret




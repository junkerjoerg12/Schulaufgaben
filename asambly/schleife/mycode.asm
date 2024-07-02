
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h 

MOV CX, 100
mov ah, 2       ;int 21h gibt aus
mov dl, '*'
        
label1:
int 21h         ; ausgabe dl 
LOOP label1     ; cx-- jmp label wenn cx != 0
ret

                 




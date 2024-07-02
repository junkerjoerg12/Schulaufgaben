
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h
mov cx, 255d
mov ah, 2d

schleife:
     mov dx, 255d
     sub dx, cx
     int 21h
     loop schleife
ret





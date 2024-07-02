
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h

mov ah, 2
mov dl,'*';* in dl weil dl ausgelesen wird
int 21h;std out

ret





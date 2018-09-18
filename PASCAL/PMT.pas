unit Unit4;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, math;
      function PMT(var i,n,monto:Double):Double;cdecl;export;
implementation
function PMT(var i,n,monto:Double):Double;cdecl;
begin
        Result:= monto*(power(1+i,n)* i / (power(1+i,n) -1) );
end;

end.


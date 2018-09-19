unit Unit5;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, crt, math;
     function F2C(var f:Double):Double;cdecl;export;
implementation
function F2C(var f:Double):Double;cdecl;
begin
  Result:= (f-32)*(5/9);
end;

end.


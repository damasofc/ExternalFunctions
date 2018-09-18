unit Unit3;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils;
  function Compare(const s1,s2: PAnsiChar):Integer;cdecl;export;
implementation
function Compare(const s1,s2: PAnsiChar):Integer;cdecl;
var rs: Integer;
begin
     rs := CompareStr(s1,s2);
  if rs > 0 then
     begin
     Result:=1;
     end
  else if rs <0 then
     begin
     Result:= -1;
     end
  else
  begin
      Result:= 0;
  end;

end;

end.


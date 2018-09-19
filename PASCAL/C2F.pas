unit Unit6;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils;
function C2F(var c:Double):Double;cdecl;export;
implementation
function C2F(var c:Double):Double;cdecl;
begin
  Result := (c * (9/5)) + 32;
end;

end.


unit Unit2;

{$mode objfpc}{$H+}

interface
                   uses
  Classes, SysUtils, pingsend;
                   function Ping(const ip: PAnsiChar):Integer;cdecl;export;
implementation
function Ping(const ip: PAnsiChar):Integer;cdecl;
var pingsend: TPingSend;
begin
      pingsend := TPINGSend.Create;
      try
      pingsend.Timeout := 5000;
      if pingsend.Ping(string(ip))= True then
      begin
                      Result := 1;
      end
      else
      begin
                    Result := 0;
      end;
      finally
        pingsend.Free;
      end;
  end;

end.


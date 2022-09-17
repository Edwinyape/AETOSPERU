import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Medicamento } from '../models/medicamento.model';




const baseUrl = 'http://localhost:8092/rest/medicamento'
@Injectable({
  providedIn: 'root'
})
export class MedicamentoService {

  constructor(private http: HttpClient) { }


ento(): Observable<Medicamento[]>{
  return this.http.get<Medicamento[]>(baseUrl);
}

consultar(filtro:string):Observable<Medicamento[]>{
  return this.http.get<Medicamento[]>(baseUrl + "/listaMedicamentoPorNombreLike/" + filtro );
}

registra(aux:Medicamento):Observable<any>{
  return this.http.post<any>(baseUrl+"/registrarMedicamento",aux);
}

actualiza(aux:Medicamento):Observable<any>{
  return this.http.put<any>(baseUrl+"/actualizaMedicamento",aux);
}

delete(id:number):Observable<any>{
return this.http.delete<any>(baseUrl+"/deleteMedicamento/"+id )
}

}

import { Component, OnInit } from '@angular/core';
import { MedicamentoService } from 'src/app/services/medicamento.service';
import { Medicamento} from 'src/app/models/medicamento.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-add-medicamento',
  templateUrl: './add-medicamento.component.html',
  styleUrls: ['./add-medicamento.component.css']
})
export class AddMedicamentoComponent implements OnInit {
   
  constructor(private medicamentoService:MedicamentoService,private formBuilder:FormBuilder) {    
  }
    //Para la Grilla
    medicamentos: Medicamento [] = [];
    filtro: string ="";
 
    ngOnInit(): void {
      this.consultar("");
    }
     //Json para registrar o actualizar
  medicamento: Medicamento = { 
    idMedicamento:0,
    nombre:"",
    precio:0,
    stock:0,
    laboratorio:"",
  };
  forms : FormGroup = this.formBuilder.group({
    nombre : ['',[Validators.required,Validators.pattern('[a-zA-ZáéíóúñüÁÉÍÓÚÑÜ\\s]{3,30}')]],
    precio : ['',[Validators.required]],
    stock : ['', [Validators.min(1)]],
    laboratorio : ['',[Validators.required,Validators.pattern('[0-9a-zA-ZáéíóúñüÁÉÍÓÚÑÜ\\s]{2,40}')]],
   
  });

 submitted = false;
  
  //consultar 
  consultar(valor:String){
    console.log("==> consultar ==> filtro ==>" + this.filtro);
    var varFiltro:string =  valor == "" ? "todo" :  this.filtro; 
     this.medicamentoService.consultar(varFiltro).subscribe(
         response => this.medicamentos= response
     );
  }

  //registrar 
   
  registra(){
    this.submitted = true;
    
    //validacion de formulario 
    if (this.forms.invalid){
      return;
    }

  //llamamos al servicio
    this.medicamentoService.registra(this.medicamento).subscribe(
        response =>{
              //2 envío el mensaje
              console.log(response.mensaje);
              alert(response.mensaje);

              //3 aCtualizo la grilla
              this.consultar("");
            
              //4 limpio el formulario
              this.medicamento = { 
                idMedicamento:0,
                nombre:"",
                precio:0,
                stock:0,
                laboratorio:"",
              }   
        },
        error => {
            console.log(error);
        },
    );
  
}


//busca medicamento por filtro de nombre
busca(aux:Medicamento){
  console.log(" ==> busca ==> id ==> " + aux.idMedicamento);
  //Actualiza el json con el registro seleccionado en la tabla
  this.medicamento = aux;
}


actualiza(){
//llamamos al servicio
  this.medicamentoService.actualiza(this.medicamento).subscribe(
    response =>{
            //1 lanzo el mensaje
            alert(response.mensaje);

            //2 actualizo la tabla
            this.consultar("");
            
             //3 limpio el formulario
            this.medicamento = { 
              idMedicamento:0,
                nombre:"",
                precio:0,
                stock:0,
                laboratorio:"",
            }   
      },
      error => {
          console.log(error);
      },
  );
}


//eliminar 
Eliminar(id:number){
  //llamamos al servicio
  this.medicamentoService.delete(id).subscribe(
    response =>{
     //1 lanzo el mensaje
      alert(response.mensaje);
      
      //2 actualizo la tabla
      this.consultar("");
    },
    error => {
      console.log(error);
    },
    
  );
}

}

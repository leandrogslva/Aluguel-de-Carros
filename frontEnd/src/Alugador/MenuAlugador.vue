<template>
    <div> 
        <v-container grid-list-xl style="margin-top: 60px">
            <v-form >
                <v-card>
                    <v-card-title class="elevation-3 indigo darken-3 title"
                    style="color:white">Cadastre um veículo
                    
                    </v-card-title>
                    <v-card-text>
                        <v-layout row wrap>
                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.placa"
                                label="Placa:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.kmRodados"
                                type="number"
                                label="KM's Rodados:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.docCarro"
                                label="Documento do Carro:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.tipoCombustivel"
                                label="Tipo do Combustível:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.marca"
                                label="Marca:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.modelo"
                                label="Modelo:">
                                </v-text-field>
                            </v-flex>

                            <v-flex>
                                <v-btn
                                color="indigo darken-2"
                                style="color: white; float: right"
                                @click="dialogoPublicarCarro = true">Publicar Carro</v-btn>    
                            </v-flex> 

                            <v-dialog v-model="dialogoPublicarCarro" max-width="280px">
                                <v-card>
                                    <v-card-title
                                    class="elevation-2 indigo darken-3"
                                    style="color:white"
                                    >Confirmar Publicação</v-card-title>
                                    <v-card-text 
                                    style="height: 200px; 
                                    font-size: 20px; 
                                    color:black">
                                        <br><br>
                                        Deseja publicar este carro para possíveis locações?
                                        <br><br><br><br>
                                            <v-btn
                                            text
                                            color="indigo darken-3"
                                            @click="dialogoPublicarCarro = false">Não
                                            </v-btn>
                                            <v-btn
                                            text
                                            style="float: right"
                                            color="indigo darken-3"
                                            @click="publicarCarro()">Sim
                                            </v-btn>       
                                    </v-card-text>
                                </v-card>
                            </v-dialog>
                        </v-layout>
                    </v-card-text>
                </v-card>
            </v-form>
        </v-container>
    </div>
</template>

<script>
import service from './AlugadorService.js'

export default {

    data(){
        return{
            dialogoPublicarCarro: false,
            carro: {
                kmRodados: '',
                docCarro: '',
                tipoCombustivel: '',
                marca: '',
                modelo: '',
                placa: ''
            },
            defaultData: {
                kmRodados: '',
                docCarro: '',
                tipoCombustivel: '',
                marca: '',
                modelo: '',
                placa: ''
            },
            
        }
    },
    methods: {
        async publicarCarro(){
            await service.salva(this.carro)
            .then(resposta => console.log(resposta))
            .catch(erro => console.log(erro))
            this.carro = this.defaultData
            this.dialogoPublicarCarro = false
        },
    }
}
</script>

<style>

</style>

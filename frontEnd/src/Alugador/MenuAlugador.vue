<template>
    <div> 
        <v-container grid-list-xl style="margin-top: 60px">
            <v-form 
            v-model="formularioCompleto"
            ref="formCarros">
                <v-card>
                    <v-card-title class="elevation-3 indigo darken-3 title"
                    style="color:white">Cadastre um veículo
                    
                    </v-card-title>
                    <v-card-text>
                        <v-layout row wrap>
                            <v-flex xs4>
                                <v-text-field
                                v-mask="placaMask"
                                v-model="carro.placa"
                                :rules="[rules.required, rules.minPlaca]"
                                label="Placa:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.kmRodados"
                                :rules="[rules.required]"
                                type="number"
                                label="KM's Rodados:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.docCarro"
                                placeholder="Digite o RENAVAM"
                                :rules="[rules.required, rules.renavam]"
                                v-mask="renavamMask"
                                label="Documento do Carro:">
                                </v-text-field>
                            </v-flex> 

                            <v-flex xs4>
                                <v-text-field
                                v-model="carro.tipoCombustivel"
                                :rules="[rules.required]"
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
                                :rules="[rules.required]"
                                label="Modelo:">
                                </v-text-field>
                            </v-flex>

                            <v-flex>
                                <v-btn
                                color="indigo darken-2"
                                style="color: white; float: right"
                                :disabled="!formularioCompleto"
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
import {mask} from 'vue-the-mask'
import service from './AlugadorService.js'

export default {
    directives: {
        mask
    },

    data(){
        return{
            formularioCompleto: false,
            placaMask: 'SSS-####',
            renavamMask: '###########',
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
            rules: {
                required: value => !!value || 'Campo Obrigatório',
                renavam: v => v.length == 11 || 'Digite um RENAVAM válido',
                minPlaca: v => v.length == 8 || 'Digite uma placa válida'
            }
        }
    },
    methods: {
        async publicarCarro(){
            await service.salva(this.carro)
            .then(resposta => console.log(resposta))
            .catch(erro => console.log(erro))
            this.carro = this.defaultData
            this.dialogoPublicarCarro = false;
            this.$refs.formCarros.reset();
        },
    }
}
</script>

<style>
    input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    }

    input[type=number] {
        -moz-appearance:textfield;
    }
</style>

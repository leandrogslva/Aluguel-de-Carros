<template>
    <div>
        <v-container style="margin-top: 60px">
            <v-container v-if="carrosAlugados != ''">
                <v-layout row wrap>
                    <v-flex xs6 v-for="locacoes in carrosAlugados"
                    :key="locacoes.id"
                    style="padding: 5px">
                        <v-card 
                        max-width="800"
                        class="mx-auto">
                            <v-toolbar color="light-blue" dark>
                                <v-toolbar-title>{{locacoes.modelo}}</v-toolbar-title>
                                <v-spacer></v-spacer>
                                    <v-btn
                                    text
                                    @click="editarCamposAluguel = !editarCamposAluguel">
                                        <v-icon color="white">mdi-border-color</v-icon>
                                    </v-btn>
                                    <v-btn
                                    text
                                    @click="dialogoDeExclusao = true;pegarLocacaoParaExcluir(locacoes)">
                                        <v-icon color="white">mdi-delete</v-icon>
                                    </v-btn>
                            </v-toolbar>
                            <v-spacer></v-spacer>
                            <v-form >
                                <v-list-item class="elevation-3">
                                    <v-list-item-content>
                                        <b>
                                
                                        <v-text-field
                                        :value="locacoes.kmRodados"
                                        disabled
                                        label="KM's Rodados:"
                                        ></v-text-field>
                                        
                                        <v-text-field
                                        :value="locacoes.docCarro"
                                        disabled
                                        label="Documento do Carro:">
                                        </v-text-field>

                                        <v-text-field
                                        :value="locacoes.tipoCombustivel"
                                        disabled
                                        label="Tipo de Combustível:">
                                        </v-text-field>

                                        <v-text-field
                                        :value="locacoes.marca"
                                        disabled
                                        label="Marca:">
                                        </v-text-field>

                                        <v-text-field
                                        :value="locacoes.modelo"
                                        disabled
                                        label="Modelo:">
                                        </v-text-field>

                                        <v-text-field
                                        :value="locacoes.placa"
                                        disabled
                                        label="Placa:">
                                        </v-text-field>

                                        <v-container grid-list-md>
                                            <v-layout row wrap>
                                                <v-flex xs4>
                                                    <v-text-field
                                                    label="Retirada:"
                                                    v-mask="dataMask"
                                                    :disabled="editarCamposAluguel"
                                                    v-model="locacoes.dataRet">
                                                    </v-text-field>
                                                </v-flex>

                                                <v-flex xs4>
                                                    <v-text-field
                                                    label="Entrega:"
                                                    v-mask="dataMask"
                                                    :disabled="editarCamposAluguel"
                                                    v-model="locacoes.dataEnt">
                                                    </v-text-field>
                                                </v-flex>

                                                <v-flex xs4>
                                                    <v-text-field
                                                    label="Valor:"
                                                    disabled
                                                    :value="locacoes.valor">
                                                    </v-text-field>
                                                </v-flex>
                                            </v-layout>
                                        </v-container>
                                        </b>
                                        <v-flex>
                                            <v-btn v-if="editarCamposAluguel != true"
                                            class="light-blue"
                                            style="color:white; float: right"
                                            @click="editarLocacao(locacoes)">
                                                Salvar
                                            </v-btn>
                                        </v-flex>
                                    </v-list-item-content>
                                </v-list-item>
                                <v-dialog v-model="dialogoDeExclusao" width="265px">
                                    <v-card>
                                        <v-card-title class="elevation-2 indigo darken-3">Confirmar Exclusão</v-card-title>
                                        <v-card-text
                                        style="font-size:20px;
                                        color:black">
                                            <br><br>
                                            Tem certeza que quer excluir esta locação?
                                            <br><br><br><br>
                                            <v-btn
                                            text
                                            color="indigo darken-3"
                                            @click="dialogoDeExclusao = false">
                                                Não
                                            </v-btn>
                                            <v-btn
                                            text
                                            style="float: right"
                                            color="indigo darken-3"
                                            @click="excluirLocacao()">
                                                Sim
                                            </v-btn>
                                        </v-card-text>
                                    </v-card>
                                </v-dialog>
                            </v-form>    
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-container>

            <v-container v-else>
                <v-card max-width="500px">
                    <v-card-title style="color:white" class="light-blue"> Ops...
                        <v-spacer></v-spacer>
                        <v-icon color="white">mdi-emoticon-sad</v-icon>
                    </v-card-title>
                    <v-card-text 
                    style="height: 250px; color: black;"
                    class="elevation-2
                    text-center
                    headline"><br><br>
                        Você não possui nenhuma locação até o momento...
                    </v-card-text>
                </v-card>
            </v-container>
            
        </v-container>
    </div>
</template>

<script>
import service from './ClienteService.js'
import { mask } from 'vue-the-mask'

export default {
     directives:{
        mask,
    },

    created(){
        this.pegarMinhasLocacoes()
    },

    data(){
        return{
            dialogoDeExclusao: false,
            carrosAlugados: [],
            dataMask: '##/##/####',
            editarCamposAluguel: true,
            objetoParaExcluir: {},
        }
    },

    methods:{
        pegarMinhasLocacoes(){
            service.todasMinhasLocacoes()
            .then(resposta => {
                console.log(resposta)
                this.carrosAlugados = resposta.data
            }).catch(erro => console.log(erro))
        },

        pegarLocacaoParaExcluir(locacoes){
            this.objetoParaExcluir = locacoes
        },

        excluirLocacao(){
            service.apaga(this.objetoParaExcluir)
            .then(resposta => console.log(resposta))
            .catch(erro => console.log(erro))
            this.carrosAlugados.splice(this.objetoParaExcluir,1)
        },

        async editarLocacao(locacoes){
            await service.atualiza(locacoes)
            .then(resposta => console.log(resposta))
            .catch(erro => console.log(erro))
            this.editarCamposAluguel = false
        }
    }
}
</script>

<style>

</style>

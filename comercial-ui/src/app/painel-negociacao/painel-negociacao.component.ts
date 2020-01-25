import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-painel-negociacao',
  templateUrl: './painel-negociacao.component.html',
  styleUrls: ['./painel-negociacao.component.css']
})
export class PainelNegociacaoComponent implements OnInit {

  oportunidades = [
    { descricao: 'Projeto de desenvolvimento de ERP', nomeProspecto: 'João', valor: 50000},
    { descricao: 'Manutençãp de CRM por 1 ano', nomeProspecto: 'Joãozinho', valor: 150000}
  ];

  constructor() { }

  ngOnInit() {
  }

}

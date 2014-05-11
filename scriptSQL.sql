/***********AREAS***********/
/*AREA DADOS*/
INSERT INTO `petic`.`petic_area`
(`id`,
`descricao`)
VALUES
(1,
"Dados");
/*AREA SOFTWARE*/
INSERT INTO `petic`.`petic_area`
(`id`,
`descricao`)
VALUES
(2,
"Software");
/*AREA HARDWARE*/
INSERT INTO `petic`.`petic_area`
(`id`,
`descricao`)
VALUES
(3,
"Hardware");
/*AREA TELECOMUNICAÇÕES*/
INSERT INTO `petic`.`petic_area`
(`id`,
`descricao`)
VALUES
(4,
"Telecomunicações");
/*AREA PESSOAS*/
INSERT INTO `petic`.`petic_area`
(`id`,
`descricao`)
VALUES
(5,
"Pessoas");

/***********SUBAREAS***********/
/*************1.x*************/
/*SUBAREA 1.1 ARMAZENAMENTO*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(1,
"Armazenamento",
"1.1",
1);
/*SUBAREA 1.2 BACKUP*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(2,
"Backup",
"1.2",
1);
/*SUBAREA 1.3 SEGURANÇA E PRIVACIDADE*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(3,
"Segurança e Privacidade",
"1.3",
1);
/*************2.x*************/
/*SUBAREA 2.1 REQUERIMENTO DE OUTRAS ÁREAS*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(4,
"Requerimento de outras áreas",
"2.1",
2);
/*SUBAREA 2.2 SUPORTE*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(5,
"Suporte",
"2.2",
2);
/*SUBAREA 2.3 SOFTWARE DE SUPORTE PARA O NEGÓCIO*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(6,
"Software de suporte para o Negócio",
"2.3",
2);
/*SUBAREA 2.4 MELHORIA DE PROCESSO DE SOFTWARE*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(7,
"Melhoria de Processo de Software",
"2.4",
2);
/*************3.x***************/
/*SUBAREA 3.1 COMPRAS*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(8,
"Compras",
"3.1",
3);
/*SUBAREA 3.2 MANUTENÇÃO*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(9,
"Manutenção",
"3.2",
3);
/*SUBAREA 3.3 SEGURANÇA*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(10,
"Segurança",
"3.3",
3);
/**************4.1***************/
/*SUBAREA 4.1 VOZ*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(11,
"Voz",
"4.1",
4);
/*SUBAREA 4.2 ESCRITA*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(12,
"Escrita",
"4.2",
4);
/*SUBAREA 4.3 REDE*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(13,
"Rede",
"4.3",
4);
/*SUBAREA 4.4 INTERNET*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(14,
"Internet",
"4.4",
4);
/***********5.1************/
/*SUBAREA 5.1 CARGOS*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(15,
"Cargos",
"5.1",
5);
/*SUBAREA 5.2 CONHECIMENTO*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(16,
"Conhecimento",
"5.2",
5);
/*SUBAREA 5.3 BEM ESTAR*/
INSERT INTO `petic`.`petic_subarea`
(`id`,
`descricao`,
`idFormatado`,
`area_id`)
VALUES
(17,
"Bem estar",
"5.3",
5);
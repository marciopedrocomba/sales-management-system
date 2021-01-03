-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 11-Mar-2020 às 11:45
-- Versão do servidor: 10.4.8-MariaDB
-- versão do PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `maseda`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE `categoria` (
  `ID` int(11) NOT NULL,
  `Nome` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `ID` int(11) NOT NULL,
  `Nome` varchar(40) NOT NULL,
  `Cidade` varchar(40) DEFAULT NULL,
  `Municipio` varchar(40) DEFAULT NULL,
  `Bairro` varchar(40) DEFAULT NULL,
  `Rua` varchar(40) DEFAULT NULL,
  `Pais` varchar(40) DEFAULT NULL,
  `Sexo` enum('M','F') DEFAULT NULL,
  `Telefone` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `encomenda`
--

CREATE TABLE `encomenda` (
  `ID` int(11) NOT NULL,
  `Servico` int(11) NOT NULL,
  `Estado` int(11) NOT NULL,
  `Cliente` int(11) NOT NULL,
  `produto` int(11) DEFAULT NULL,
  `Quantidade` int(11) DEFAULT NULL,
  `Data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura stand-in para vista `encomendaefectuada`
-- (Veja abaixo para a view atual)
--
CREATE TABLE `encomendaefectuada` (
`Nome` varchar(40)
,`Total` decimal(32,0)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `equipamento`
--

CREATE TABLE `equipamento` (
  `ID` int(11) NOT NULL,
  `Nome` varchar(40) NOT NULL,
  `Estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `equipamento_servico`
--

CREATE TABLE `equipamento_servico` (
  `Equipamento` int(11) NOT NULL,
  `Servico` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `estado_encomenda`
--

CREATE TABLE `estado_encomenda` (
  `ID` int(11) NOT NULL,
  `Nome` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `estado_encomenda`
--

INSERT INTO `estado_encomenda` (`ID`, `Nome`) VALUES
(2, 'em processo'),
(3, 'efectuada');

-- --------------------------------------------------------

--
-- Estrutura da tabela `estado_equipamento`
--

CREATE TABLE `estado_equipamento` (
  `ID` int(11) NOT NULL,
  `Nome` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE `fornecedor` (
  `ID` int(11) NOT NULL,
  `Nome` varchar(20) NOT NULL,
  `Cidade` varchar(40) DEFAULT NULL,
  `Municipio` varchar(40) DEFAULT NULL,
  `Bairro` varchar(40) DEFAULT NULL,
  `Pais` varchar(50) DEFAULT NULL,
  `Rua` varchar(50) DEFAULT NULL,
  `Telefone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcao`
--

CREATE TABLE `funcao` (
  `ID` int(11) NOT NULL,
  `Nome` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcao`
--

INSERT INTO `funcao` (`ID`, `Nome`) VALUES
(1, 'Recepcionista'),
(2, 'Administrador'),
(3, 'simples');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `BI` varchar(20) NOT NULL,
  `Nome` varchar(40) NOT NULL,
  `Sobrenome` varchar(40) NOT NULL,
  `Data_Nascimento` date NOT NULL,
  `Telefone` int(11) NOT NULL,
  `Cidade` varchar(40) DEFAULT NULL,
  `Municipio` varchar(40) DEFAULT NULL,
  `Bairro` varchar(40) DEFAULT NULL,
  `Rua` varchar(40) DEFAULT NULL,
  `Sexo` enum('M','F') NOT NULL,
  `Email` varchar(40) DEFAULT NULL,
  `Funcao` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `item_encomendado`
--

CREATE TABLE `item_encomendado` (
  `encomenda` int(11) NOT NULL,
  `venda` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `item_vendido`
--

CREATE TABLE `item_vendido` (
  `Produto` int(11) NOT NULL,
  `Venda` int(11) NOT NULL,
  `Quantidade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pagamento`
--

CREATE TABLE `pagamento` (
  `ID` int(11) NOT NULL,
  `Tipo` int(11) NOT NULL,
  `Valor_apagar` double DEFAULT NULL,
  `valor_dinheiro` double DEFAULT 0,
  `valor_cartao` double DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `ID` int(11) NOT NULL,
  `Preco` double NOT NULL,
  `Quantidade` int(11) NOT NULL,
  `Categoria` int(11) NOT NULL,
  `Tipo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura stand-in para vista `produtoentrada`
-- (Veja abaixo para a view atual)
--
CREATE TABLE `produtoentrada` (
`ID` int(11)
,`Fornecedor` varchar(20)
,`Produto` varchar(40)
,`Quantidade` int(11)
,`Data` date
);

-- --------------------------------------------------------

--
-- Estrutura stand-in para vista `produtosaida`
-- (Veja abaixo para a view atual)
--
CREATE TABLE `produtosaida` (
`produto` varchar(40)
,`total de saida` decimal(32,0)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto_entrada`
--

CREATE TABLE `produto_entrada` (
  `ID` int(11) NOT NULL,
  `Data` date NOT NULL,
  `Quantidade` int(11) NOT NULL,
  `Fornecedor` int(11) NOT NULL,
  `Produto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `servico`
--

CREATE TABLE `servico` (
  `ID` int(11) NOT NULL,
  `Nome` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipo_pagamento`
--

CREATE TABLE `tipo_pagamento` (
  `ID` int(11) NOT NULL,
  `Nome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tipo_pagamento`
--

INSERT INTO `tipo_pagamento` (`ID`, `Nome`) VALUES
(1, 'Dinheiro'),
(2, 'Cartão'),
(3, 'Misto');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipo_produto`
--

CREATE TABLE `tipo_produto` (
  `ID` int(11) NOT NULL,
  `Nome` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

CREATE TABLE `user` (
  `Funcionario` varchar(20) NOT NULL,
  `Username` varchar(40) NOT NULL,
  `Senha` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura stand-in para vista `userfuncionario`
-- (Veja abaixo para a view atual)
--
CREATE TABLE `userfuncionario` (
`username` varchar(40)
,`password` varchar(40)
,`funcao` varchar(40)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE `venda` (
  `ID` int(11) NOT NULL,
  `Pagamento` int(11) NOT NULL,
  `Cliente` int(11) DEFAULT NULL,
  `Funcionario` varchar(40) NOT NULL,
  `Data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura stand-in para vista `vendaservico`
-- (Veja abaixo para a view atual)
--
CREATE TABLE `vendaservico` (
`ID` int(11)
,`funcionario` varchar(40)
,`total` double
,`Data` date
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda_servico`
--

CREATE TABLE `venda_servico` (
  `ID` int(11) NOT NULL,
  `Pagamento` int(11) NOT NULL,
  `Funcionario` varchar(30) NOT NULL,
  `Data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para vista `encomendaefectuada`
--
DROP TABLE IF EXISTS `encomendaefectuada`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `encomendaefectuada`  AS  select `categoria`.`Nome` AS `Nome`,sum(`encomenda`.`Quantidade`) AS `Total` from ((`encomenda` join `produto` on(`encomenda`.`produto` = `produto`.`ID`)) join `categoria` on(`produto`.`Categoria` = `categoria`.`ID`)) where `encomenda`.`Estado` = (select `estado_encomenda`.`ID` from `estado_encomenda` where `estado_encomenda`.`Nome` = 'efectuada') group by `encomenda`.`produto` ;

-- --------------------------------------------------------

--
-- Estrutura para vista `produtoentrada`
--
DROP TABLE IF EXISTS `produtoentrada`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `produtoentrada`  AS  select `produto_entrada`.`ID` AS `ID`,`fornecedor`.`Nome` AS `Fornecedor`,`categoria`.`Nome` AS `Produto`,`produto_entrada`.`Quantidade` AS `Quantidade`,`produto_entrada`.`Data` AS `Data` from ((((`produto_entrada` join `fornecedor` on(`produto_entrada`.`Fornecedor` = `fornecedor`.`ID`)) join `produto` on(`produto_entrada`.`Produto` = `produto`.`ID`)) join `categoria` on(`produto`.`Categoria` = `categoria`.`ID`)) join `tipo_produto` on(`produto`.`Tipo` = `tipo_produto`.`ID`)) order by `produto_entrada`.`Data` ;

-- --------------------------------------------------------

--
-- Estrutura para vista `produtosaida`
--
DROP TABLE IF EXISTS `produtosaida`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `produtosaida`  AS  select `categoria`.`Nome` AS `produto`,sum(`item_vendido`.`Quantidade`) AS `total de saida` from (((((((((`item_vendido` join `produto` on(`item_vendido`.`Produto` = `produto`.`ID`)) join `categoria` on(`produto`.`Categoria` = `categoria`.`ID`)) join `tipo_produto` on(`produto`.`Tipo` = `tipo_produto`.`ID`)) join `venda` on(`item_vendido`.`Venda` = `venda`.`ID`)) join `pagamento` on(`venda`.`Pagamento` = `pagamento`.`ID`)) join `tipo_pagamento` on(`pagamento`.`Tipo` = `tipo_pagamento`.`ID`)) join `cliente` on(`venda`.`Cliente` = `cliente`.`ID`)) join `funcionario` on(`venda`.`Funcionario` = `funcionario`.`BI`)) join `funcao` on(`funcionario`.`Funcao` = `funcao`.`ID`)) group by `item_vendido`.`Produto` ;

-- --------------------------------------------------------

--
-- Estrutura para vista `userfuncionario`
--
DROP TABLE IF EXISTS `userfuncionario`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `userfuncionario`  AS  select `user`.`Username` AS `username`,`user`.`Senha` AS `password`,`funcao`.`Nome` AS `funcao` from ((`user` join `funcionario` on(`user`.`Funcionario` = `funcionario`.`BI`)) join `funcao` on(`funcionario`.`Funcao` = `funcao`.`ID`)) ;

-- --------------------------------------------------------

--
-- Estrutura para vista `vendaservico`
--
DROP TABLE IF EXISTS `vendaservico`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vendaservico`  AS  select `venda_servico`.`ID` AS `ID`,`funcionario`.`Nome` AS `funcionario`,`pagamento`.`Valor_apagar` AS `total`,`venda_servico`.`Data` AS `Data` from ((`venda_servico` join `pagamento` on(`venda_servico`.`Pagamento` = `pagamento`.`ID`)) join `funcionario` on(`venda_servico`.`Funcionario` = `funcionario`.`BI`)) ;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`ID`);

--
-- Índices para tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`ID`);

--
-- Índices para tabela `encomenda`
--
ALTER TABLE `encomenda`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Servico` (`Servico`),
  ADD KEY `Estado` (`Estado`),
  ADD KEY `Cliente` (`Cliente`),
  ADD KEY `produto` (`produto`);

--
-- Índices para tabela `equipamento`
--
ALTER TABLE `equipamento`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Estado` (`Estado`);

--
-- Índices para tabela `equipamento_servico`
--
ALTER TABLE `equipamento_servico`
  ADD PRIMARY KEY (`Equipamento`,`Servico`),
  ADD KEY `Servico` (`Servico`);

--
-- Índices para tabela `estado_encomenda`
--
ALTER TABLE `estado_encomenda`
  ADD PRIMARY KEY (`ID`);

--
-- Índices para tabela `estado_equipamento`
--
ALTER TABLE `estado_equipamento`
  ADD PRIMARY KEY (`ID`);

--
-- Índices para tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD PRIMARY KEY (`ID`);

--
-- Índices para tabela `funcao`
--
ALTER TABLE `funcao`
  ADD PRIMARY KEY (`ID`);

--
-- Índices para tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`BI`),
  ADD KEY `Funcao` (`Funcao`);

--
-- Índices para tabela `item_encomendado`
--
ALTER TABLE `item_encomendado`
  ADD PRIMARY KEY (`encomenda`,`venda`),
  ADD KEY `venda` (`venda`);

--
-- Índices para tabela `item_vendido`
--
ALTER TABLE `item_vendido`
  ADD PRIMARY KEY (`Produto`,`Venda`),
  ADD KEY `Venda` (`Venda`);

--
-- Índices para tabela `pagamento`
--
ALTER TABLE `pagamento`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Tipo` (`Tipo`);

--
-- Índices para tabela `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Categoria_2` (`Categoria`),
  ADD KEY `Categoria` (`Categoria`),
  ADD KEY `Tipo` (`Tipo`);

--
-- Índices para tabela `produto_entrada`
--
ALTER TABLE `produto_entrada`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Fornecedor` (`Fornecedor`),
  ADD KEY `Produto` (`Produto`);

--
-- Índices para tabela `servico`
--
ALTER TABLE `servico`
  ADD PRIMARY KEY (`ID`);

--
-- Índices para tabela `tipo_pagamento`
--
ALTER TABLE `tipo_pagamento`
  ADD PRIMARY KEY (`ID`);

--
-- Índices para tabela `tipo_produto`
--
ALTER TABLE `tipo_produto`
  ADD PRIMARY KEY (`ID`);

--
-- Índices para tabela `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Funcionario`);

--
-- Índices para tabela `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Pagamento` (`Pagamento`),
  ADD KEY `Cliente` (`Cliente`),
  ADD KEY `Funcionario` (`Funcionario`);

--
-- Índices para tabela `venda_servico`
--
ALTER TABLE `venda_servico`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `venda_servico_ibfk_1` (`Funcionario`),
  ADD KEY `venda_servico_ibfk_2` (`Pagamento`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `categoria`
--
ALTER TABLE `categoria`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `encomenda`
--
ALTER TABLE `encomenda`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `equipamento`
--
ALTER TABLE `equipamento`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `equipamento_servico`
--
ALTER TABLE `equipamento_servico`
  MODIFY `Equipamento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `estado_encomenda`
--
ALTER TABLE `estado_encomenda`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `estado_equipamento`
--
ALTER TABLE `estado_equipamento`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `funcao`
--
ALTER TABLE `funcao`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `pagamento`
--
ALTER TABLE `pagamento`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `produto`
--
ALTER TABLE `produto`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `produto_entrada`
--
ALTER TABLE `produto_entrada`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `servico`
--
ALTER TABLE `servico`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `tipo_pagamento`
--
ALTER TABLE `tipo_pagamento`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `tipo_produto`
--
ALTER TABLE `tipo_produto`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `venda`
--
ALTER TABLE `venda`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `venda_servico`
--
ALTER TABLE `venda_servico`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `encomenda`
--
ALTER TABLE `encomenda`
  ADD CONSTRAINT `encomenda_ibfk_1` FOREIGN KEY (`Servico`) REFERENCES `servico` (`ID`),
  ADD CONSTRAINT `encomenda_ibfk_2` FOREIGN KEY (`Estado`) REFERENCES `estado_encomenda` (`ID`),
  ADD CONSTRAINT `encomenda_ibfk_3` FOREIGN KEY (`Cliente`) REFERENCES `cliente` (`ID`),
  ADD CONSTRAINT `encomenda_ibfk_4` FOREIGN KEY (`produto`) REFERENCES `produto` (`ID`);

--
-- Limitadores para a tabela `equipamento`
--
ALTER TABLE `equipamento`
  ADD CONSTRAINT `equipamento_ibfk_1` FOREIGN KEY (`Estado`) REFERENCES `estado_equipamento` (`ID`);

--
-- Limitadores para a tabela `equipamento_servico`
--
ALTER TABLE `equipamento_servico`
  ADD CONSTRAINT `equipamento_servico_ibfk_1` FOREIGN KEY (`Equipamento`) REFERENCES `equipamento` (`ID`),
  ADD CONSTRAINT `equipamento_servico_ibfk_2` FOREIGN KEY (`Servico`) REFERENCES `servico` (`ID`);

--
-- Limitadores para a tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`Funcao`) REFERENCES `funcao` (`ID`);

--
-- Limitadores para a tabela `item_encomendado`
--
ALTER TABLE `item_encomendado`
  ADD CONSTRAINT `item_encomendado_ibfk_1` FOREIGN KEY (`encomenda`) REFERENCES `encomenda` (`ID`),
  ADD CONSTRAINT `item_encomendado_ibfk_2` FOREIGN KEY (`venda`) REFERENCES `venda_servico` (`ID`);

--
-- Limitadores para a tabela `item_vendido`
--
ALTER TABLE `item_vendido`
  ADD CONSTRAINT `item_vendido_ibfk_1` FOREIGN KEY (`Produto`) REFERENCES `produto` (`ID`),
  ADD CONSTRAINT `item_vendido_ibfk_2` FOREIGN KEY (`Venda`) REFERENCES `venda` (`ID`);

--
-- Limitadores para a tabela `pagamento`
--
ALTER TABLE `pagamento`
  ADD CONSTRAINT `pagamento_ibfk_1` FOREIGN KEY (`Tipo`) REFERENCES `tipo_pagamento` (`ID`);

--
-- Limitadores para a tabela `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`Categoria`) REFERENCES `categoria` (`ID`),
  ADD CONSTRAINT `produto_ibfk_2` FOREIGN KEY (`Tipo`) REFERENCES `tipo_produto` (`ID`);

--
-- Limitadores para a tabela `produto_entrada`
--
ALTER TABLE `produto_entrada`
  ADD CONSTRAINT `produto_entrada_ibfk_1` FOREIGN KEY (`Fornecedor`) REFERENCES `fornecedor` (`ID`),
  ADD CONSTRAINT `produto_entrada_ibfk_2` FOREIGN KEY (`Produto`) REFERENCES `produto` (`ID`);

--
-- Limitadores para a tabela `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`Funcionario`) REFERENCES `funcionario` (`BI`);

--
-- Limitadores para a tabela `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`Pagamento`) REFERENCES `pagamento` (`ID`),
  ADD CONSTRAINT `venda_ibfk_2` FOREIGN KEY (`Cliente`) REFERENCES `cliente` (`ID`),
  ADD CONSTRAINT `venda_ibfk_3` FOREIGN KEY (`Funcionario`) REFERENCES `funcionario` (`BI`);

--
-- Limitadores para a tabela `venda_servico`
--
ALTER TABLE `venda_servico`
  ADD CONSTRAINT `venda_servico_ibfk_1` FOREIGN KEY (`Funcionario`) REFERENCES `funcionario` (`BI`),
  ADD CONSTRAINT `venda_servico_ibfk_2` FOREIGN KEY (`Pagamento`) REFERENCES `pagamento` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

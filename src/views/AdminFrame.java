package views;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.TableRowSorter;

import com.itextpdf.text.DocumentException;
import com.toedter.calendar.*;

import ctrls.Dbh;
import ctrls.FaturaMazeda;
import dal.*;

public class AdminFrame extends JFrame{

	//todas as declarações estara aqui :)
	private CardLayout MainCL = new CardLayout();
	private CardLayout cl = new CardLayout();
	private CardLayout navbarCL = new CardLayout();
	private JPanel cardLayoutMainPanel = new JPanel();
	private JPanel RootPanel = new JPanel();
	private JPanel navbar = new JPanel();
	//private JTabbedPane about = new JTabbedPane();
	JTextArea aboutArea = new JTextArea();
	private JTextField produtoSearchField = new JTextField();
	private JTextField categoriaSearchField = new JTextField();
	private JTextField tipoProdutoSearchField = new JTextField();
	private JTextField funcionarioSearchField = new JTextField();
	private JTextField fornecedoreSearchField = new JTextField();
	private JTextField clienteSearchField = new JTextField();
	private JTextField userSearchField = new JTextField();
	private JTextField vendaSearchField = new JTextField();
	private JTextField vendaServicoSearchField = new JTextField();
	private JTextField pagamentoSearchField = new JTextField();
	private JTextField equipamentoSearchField = new JTextField();
	private JTextField equipamentoEstadoSearchField = new JTextField();
	private JTextField servicoSearchField = new JTextField();
	private JTextField encomendaSearchField = new JTextField();
	private JTextField encomendaEstadoSearchField = new JTextField();
	private JTextField stockProdutoEntradaSearchField = new JTextField();
	private JTextField stockProdutoSaidaSearchField = new JTextField();
	private JTextField stockEncomendaEfectuadaSearchField = new JTextField();
	private JTextField venderProdutoSearchField = new JTextField();

	private JProgressBar progressBar = new JProgressBar();
	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();

	/*functionarios inicializacoes*/
	private JTextField funcionarioBI = new JTextField();
	private JTextField funcionarioNome = new JTextField();
	private JTextField funcionarioSobrenome = new JTextField();
	private JDateChooser funcionarioNascimento = new JDateChooser();
	private JTextField funcionarioTelefone = new JTextField();
	private JTextField funcionarioCidade = new JTextField();
	private JTextField funcionarioMunicipio = new JTextField();
	private JTextField funcionarioBairro = new JTextField();
	private JTextField funcionarioRua = new JTextField();
	private JRadioButton funcionarioSexoM = new JRadioButton("M");
	private JRadioButton funcionarioSexoF = new JRadioButton("F");
	private JTextField funcionarioEmail = new JTextField();
	private JComboBox<String> funcionarioFuncao = new JComboBox<String>();

	/*fornecedores inicializacoes*/
	private JTextField fornecedoreNome = new JTextField();
	private JTextField fornecedoreCidade = new JTextField();
	private JTextField fornecedoreMunicipio = new JTextField();
	private JTextField fornecedoreBairro = new JTextField();
	private JTextField fornecedorePais = new JTextField();
	private JTextField fornecedoreRua = new JTextField();
	private JTextField fornecedoreTelefone = new JTextField();

	/*clientes inicializacoes*/
	private JTextField clienteNome = new JTextField();
	private JTextField clienteCidade = new JTextField();
	private JTextField clienteMunicipio = new JTextField();
	private JTextField clienteBairro = new JTextField();
	private JTextField clientePais = new JTextField();
	private JTextField clienteRua = new JTextField();
	private JTextField clienteTelefone = new JTextField();
	private JRadioButton clienteSexoM = new JRadioButton("M");
	private JRadioButton clienteSexoF = new JRadioButton("F");

	/*produtos inicializacoes*/
	private JTextField produtoCategoriaNome = new JTextField();
	private JTextField produtoTipoNome = new JTextField();
	private JTextField produtoPreco = new JTextField();
	private JTextField produtoQuantidade = new JTextField();
	private JComboBox<String> produtoCategoria = new JComboBox<String>();
	private JComboBox<String> produtoTipo = new JComboBox<String>();

	/*usuarios inicializacoes*/
	private JComboBox<String> userFuncionario = new JComboBox<String>();
	private JTextField userUsername = new JTextField();
	private JPasswordField userPassword = new JPasswordField();

	/*equipamentos inicializacoes*/
	private JComboBox<String> equipamentoEstado = new JComboBox<String>();
	private JTextField equipamentoEstadoNome = new JTextField();
	private JTextField equipamentoNome = new JTextField();

	/*serviços inicializacoes*/
	private JTextField servicoNome = new JTextField();

	/*encomendas inicializacoes*/
	private JTextField encomendaEstadoNome = new JTextField();
	private JComboBox<String> encomendaServico = new JComboBox<String>();
	private JComboBox<String> encomendaEstado = new JComboBox<String>();
	private JComboBox<String> encomendaCliente = new JComboBox<String>();
	private JComboBox<String> encomendaProduto = new JComboBox<String>();
	private JTextField encomendaQuantidade = new JTextField();
	private JDateChooser encomendaData = new JDateChooser();

	/*produto de entrada inicializaçoes*/
	private JDateChooser produtoEntradaData = new JDateChooser();
	private JTextField produtoEntradaQuantidade = new JTextField();
	private JComboBox<String> produtoEntradaFornecedor = new JComboBox<String>();
	private JComboBox<String> produtoEntradaProduto = new JComboBox<String>();

	/*vender produto inicializacoes*/
	private JComboBox<String> venderCliente = new JComboBox<String>();
	private JComboBox<String> venderServicoCliente = new JComboBox<String>();
	private JComboBox<String> venderProduto = new JComboBox<String>();
	private JTextField venderQuantidade = new JTextField();
	private JComboBox<String> tipoPagamento = new JComboBox<String>();
	private JComboBox<String> tipoPagamentoServico = new JComboBox<String>();

	/*estatistica*/
	private JLabel funcionarioCount = Tools.createLabel("", null, Tools.header3);
	private JLabel clientCount = Tools.createLabel("", null, Tools.header3);
	private JLabel fornecedorCount = Tools.createLabel("", null, Tools.header3);
	private JLabel encomendaCount = Tools.createLabel("", null, Tools.header3);
	private JLabel vendasCount = Tools.createLabel("", null, Tools.header3);
	
	JLabel vendaNumber = Tools.createLabel("0", null, Tools.paragraph);
	
	/*tables inicializaçoes*/
	private JTable FuncionariosTable;
	private JTable ClientesTable;
	private JTable FornecedoresTable;
	private JTable ProdutosTable;
	private JTable ProdutoCategoriasTable;
	private JTable ProdutoTiposTable;
	private JTable UsersTable;
	private JTable PagamentosTable;
	private JTable EquipamentosTable;
	private JTable EquipamentosEstadoTable;
	private JTable ServicosTable;
	private JTable EncomendasEstadoTable;
	private JTable VendaProdutoTable;
	private JTable itemSelecionadoTable;
	private JTable venderServicoItemSelecionadoTable;
	private JTable venderProdutoTable;
	private JTable encomendasTable;
	private JTable vendasEfectuadas;
	private JTable produtosSaidaTable;
	private JTable EncomendaEfectuadaTable;
	private JTable produtosEntradaTable;
	private JTable VendasServicoTable;

	/*for database tables*/
	private FuncionariosAll funcionarios = new FuncionariosAll();
	private ClienteAll clientes = new ClienteAll();
	private FornecedoresAll fornecedores = new FornecedoresAll();
	private ProdutosAll produtos = new ProdutosAll();
	private dal.VenderProduto venderProdutos = new dal.VenderProduto();
	private CategoriasAll categorias = new CategoriasAll();
	private TipoProdutosAll tipos = new TipoProdutosAll();
	private UsersAll users = new UsersAll();
	private PagamentosAll pagamentos = new PagamentosAll();
	private EquipamentosAll equipamentos = new EquipamentosAll();
	private EquipamentosEstadoAll estadosEquipamento = new EquipamentosEstadoAll();
	private ServicosAll servicos = new ServicosAll();
	private EncomendasEstadoAll estadosEncomenda = new EncomendasEstadoAll();
	private EncomendaAll encomendas = new EncomendaAll();
	private VendaAll vendas = new VendaAll();
	private ProdutoSaidaAll produtosSaida = new ProdutoSaidaAll();
	private dal.EncomendaEfectuada encomendasEfectuadas = new EncomendaEfectuada();
	private ProdutoEntradaAll produtosEntrada = new ProdutoEntradaAll();
	private dal.VendaServicoAll vendasServico = new VendaServicoAll();

	/*Inicializando Variaves*/
	private JButton atualizarFuncionario;
	private JButton cadastrarFuncionario;
	private JButton atualizarCliente;
	private JButton cadastrarCliente;
	private JButton atualizarFornecedor;
	private JButton cadastrarFornecedor;
	private JButton cadastrarProduto;
	private JButton atualizarProduto;
	private JButton cadastrarProdutoCategoria;
	private JButton atualizarProdutoCategoria;
	private JButton cadastrarProdutoTipo;
	private JButton atualizarProdutoTipo;
	private JButton cadastrarUser;
	private JButton atualizarUser;
	private JButton cadastrarEquipamento;
	private JButton atualizarEquipamento;
	private JButton cadastrarEquipamentoEstado;
	private JButton atualizarEquipamentoEstado;
	private JButton cadastrarServico;
	private JButton atualizarServico;
	private JButton cadastrarEncomendaEstado;
	private JButton atualizarEncomendaEstado;
	private JButton cadastrarProdutoEntrada;
	private JButton atualizarProdutoEntrada;
	private JButton cadastrarEncomenda;
	private JButton atualizarEncomenda;
	private JButton EncomendaMoreOptionsReverse;

	/*para o metodo de pesquisa*/
	private TableRowSorter<dal.FuncionariosAll> funcionariosSorter;
	private RowFilter<dal.FuncionariosAll, Object> funcionariosFilter;
	private TableRowSorter<dal.FornecedoresAll> fornecedoresSorter;
	private RowFilter<dal.FornecedoresAll, Object> fornecedoresFilter;
	private TableRowSorter<dal.ClienteAll> clientesSorter;
	private RowFilter<dal.ClienteAll, Object> clientesFilter;
	private TableRowSorter<dal.ProdutosAll> produtosSorter;
	private RowFilter<dal.ProdutosAll, Object> produtosFilter;
	private TableRowSorter<dal.CategoriasAll> produtosCategoriaSorter;
	private RowFilter<dal.CategoriasAll, Object> produtosCategoriaFilter;
	private TableRowSorter<dal.TipoProdutosAll> produtosTipoSorter;
	private RowFilter<dal.TipoProdutosAll, Object> produtosTipoFilter;
	private TableRowSorter<dal.UsersAll> usersSorter;
	private RowFilter<dal.UsersAll, Object> usersFilter;
	private TableRowSorter<dal.PagamentosAll> pagamentosSorter;
	private RowFilter<dal.PagamentosAll, Object> pagamentosFilter;
	private TableRowSorter<dal.EquipamentosAll> equipamentosSorter;
	private RowFilter<dal.EquipamentosAll, Object> equipamentosFilter;
	private TableRowSorter<dal.EquipamentosEstadoAll> equipamentosEstadoSorter;
	private RowFilter<dal.EquipamentosEstadoAll, Object> equipamentosEstadoFilter;
	private TableRowSorter<dal.ServicosAll> servicosSorter;
	private RowFilter<dal.ServicosAll, Object> servicosFilter;
	private TableRowSorter<dal.EncomendasEstadoAll> encomendaEstadoSorter;
	private RowFilter<dal.EncomendasEstadoAll, Object> encomendaEstadoFilter;
	private TableRowSorter<dal.EncomendaAll> encomendaSorter;
	private RowFilter<dal.EncomendaAll, Object> encomendaFilter;
	private TableRowSorter<dal.VendaAll> vendaSorter;
	private RowFilter<dal.VendaAll, Object> vendaFilter;
	private TableRowSorter<dal.ProdutoSaidaAll> produtoSaidaSorter;
	private RowFilter<dal.ProdutoSaidaAll, Object> produtoSaidaFilter;
	private TableRowSorter<dal.EncomendaEfectuada> encomendaEfectuadaSorter;
	private RowFilter<dal.EncomendaEfectuada, Object> encomendaEfectuadaFilter;
	private TableRowSorter<dal.ProdutoEntradaAll> produtoEntradaSorter;
	private RowFilter<dal.ProdutoEntradaAll, Object> produtoEntradaFilter;
	private TableRowSorter<dal.VendaServicoAll> vendaServicoSorter;
	private RowFilter<dal.VendaServicoAll, Object> vendaServicoFilter;

	private int selectedRow;
	private JLabel usernameLabel;
	private JLabel otherUsernameLabel;
	private int id;
	private String BIfuncionario;
	//private float c = 0;
	private FaturaMazeda faturaMazeda = new FaturaMazeda();

	public AdminFrame() {
		setTitle("Publicidade Maseda");
		setSize(1096, 558);
		setIconImage(new ImageIcon("Capturar.PNG").getImage());
		setLocationRelativeTo(null);
		systemUI();
		createWindow();
		setTimer();
		addListeners();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	//metodo que cria a tela
	private void createWindow() {
		RootPanel.setLayout(MainCL);
		RootPanel.add(loginPanel(), "login");
		RootPanel.add(loadingWindow(), "loading");
		RootPanel.add(createAdminMainPanel(), "adminMain");
		MainCL.show(RootPanel, "loading");
		add(RootPanel);

	}
	
	private void systemUI() {
		String os = System.getProperty("os.name");
		
		if(os.contains("Windows")) {
			this.createSystemUI("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}else {
			this.createSystemUI("javax.swing.plaf.metal.MetalLookAndFeel");
		}
	}
	
	private void createSystemUI(String UI) {
		try {
			UIManager.setLookAndFeel(UI);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "unable to update the UI!");
		}
	}

	private JPanel createAdminMainPanel() {
		//createMenu();
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createNavbar(), BorderLayout.NORTH);
		panel.add(AdminMainContent());
		return panel; 
	}

	private JPanel createNavbar() {
		navbar.setLayout(navbarCL);
		navbar.add(createMainTop(), "adminMainTop");
		navbar.add(createOtherMainTop(), "otherMainTop");
		return navbar;
	}

	//a tela de login
	private JPanel loginPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.setBorder(BorderFactory.createEmptyBorder(120, 50, 120, 50));
		panel.add(loginContentPanel());
		return panel;
	}

	private JPanel loginContentPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
		panel.setBorder(BorderFactory.createLineBorder(Tools.red));
		JLabel logo = Tools.createLabel("", Tools.createIcon("/img/icons8_Lock_96px_1.png", 200), null);
		panel.add(logo);
		panel.add(userLoginInputsPanel());
		panel.add(accessLoginPanel());
		return panel;
	}

	//panel onde vai ficar o username e password field em tela de login
	private JPanel userLoginInputsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createEmptyBorder(100, 10, 50, 10));
		JLabel lbl1 = new JLabel("informe o usuario");
		lbl1.setFont(Tools.paragraph);
		addComponent(panel, lbl1, 20, 50, 200, 30);
		this.username.setFont(Tools.paragraph);
		addComponent(panel, username, 20, 80, 200, 30);
		JLabel lbl2 = new JLabel("informe a senha");
		lbl2.setFont(Tools.paragraph);
		addComponent(panel, lbl2, 20, 150, 200, 30);
		this.password.setFont(Tools.paragraph);
		addComponent(panel, password, 20, 180, 200, 30);
		return panel;
	}

	private JPanel accessLoginPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
		panel.add(accessLoginContentParentPanel());
		return panel;
	}

	private JPanel accessLoginContentParentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Tools.black));
		panel.add(accessLoginContentPanel());
		return panel;
	}

	private JPanel accessLoginContentPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		JButton btn1 = Tools.createButton("Login", Tools.createIcon("/img/icons8_Checked_96px.png", 30), 
				Tools.white, 100, 100, this::login);
		btn1.setBorder(BorderFactory.createLineBorder(Tools.black));
		JButton btn2 = Tools.createButton("Sair", Tools.createIcon("/img/icons8_Exit_Sign_96px.png", 30), 
				Tools.white, 100, 100, this::exit);
		btn2.setBorder(BorderFactory.createLineBorder(Tools.black));
		panel.add(btn1);
		panel.add(btn2);
		return panel;
	}

	//a tela de carregamento
	private JPanel loadingWindow() {
		JPanel panel = new JPanel(new BorderLayout());
		progressBar.setStringPainted(true);
		progressBar.setForeground(new Color(199, 56, 112));
		JLabel label = Tools.createLabel("", Tools.createIcon("/img/Capturar.PNG", this.getWidth(), this.getHeight()), null);
		panel.add(progressBar, BorderLayout.SOUTH);
		panel.add(label);
		return panel;
	}


	//metodo para criar a parte superior do Frame
	private JPanel createMainTop() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createToolBar());
		panel.add(createLogo(), BorderLayout.EAST);
		return panel;
	}

	private JPanel createOtherMainTop() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createOtherToolBar());
		panel.add(createLogo(), BorderLayout.EAST);
		return panel;
	}

	//metodo para criar o logoTipo da Maseda
	private JPanel createLogo() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		JLabel icon = Tools.createLabel("", Tools.createIcon("/img/Capturar.PNG", 100, 40), Tools.header);
		panel.add(icon);
		return panel;
	}

	//criação de logo termina aqui

	//metodo para criação da barra de menu
	private void createMenu() {
		JMenuBar menubar = new JMenuBar();
		menubar.setBackground(Tools.white);
		menubar.add(createFileMenu());
		menubar.add(createCadastrosMenu());
		menubar.add(createViewMenu());
		menubar.add(createAjudaMenu());
		setJMenuBar(menubar);
	}
	//criação da barra de menu termina aqui

	//metodos para criação do menu
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("Ficheiro");
		menu.add(createMenuItem("Sair", this::logout));
		menu.addSeparator();
		menu.add(createMenuItem("Fechar", this::exit));
		return menu;
	}

	private JMenu createCadastrosMenu() {
		JMenu menu = new JMenu("Cadastros");
		menu.add(createMenuItem("Adicionar funcionarios", this::addFuncionarios));
		menu.add(createMenuItem("Adicionar clientes", this::addClientes));
		menu.add(createMenuItem("Adicionar fornecedores", this::addFornecedores));
		menu.add(createMenuItem("Adicionar produtos", this::addProduto));
		
		menu.addSeparator();
		
		menu.add(createMenuItem("Adicionar usuarios", this::addUsuarios));
		menu.add(createMenuItem("Efectuar encomendas", this::addEncomendas));
		menu.add(createMenuItem("Fazer entrada de produto", this::addProdutoEntrada));
		
		menu.addSeparator();
		
		menu.add(createMenuItem("Fazer Vendas", this::vendaProduto));
		menu.add(createMenuItem("Fazer entrega de encomendas", this::vendaServico));
		
		return menu;
	}

	private JMenu createViewMenu() {
		JMenu menu = new JMenu("Ver");
		menu.add(createMenuItem("Funcionarios", this::viewFuncionarios));
		menu.add(createMenuItem("Clientes", this::viewClientes));
		menu.add(createMenuItem("Fornecedores", this::viewFornecedores));
		menu.add(createMenuItem("Produtos", this::viewProdutos));
		menu.addSeparator();
		menu.add(createMenuItem("Equipamentos", this::viewEquipamentosInfo));
		menu.add(createMenuItem("Serviços", this::viewServicos));
		menu.add(createMenuItem("Usuarios", this::viewUsers));
		menu.add(createMenuItem("Pagamentos", this::viewPagamentos));
		menu.addSeparator();
		menu.add(createMenuItem("Encomendas", this::viewEncomendas));
		menu.add(createMenuItem("Produtos que entraram", this::viewStockProdutoEntrada));
		menu.add(createMenuItem("Produtos que saíram", this::viewStockProdutoSaida));
		menu.add(createMenuItem("Produtos que saíram 2", this::viewStockProdutoEncomendaEfectuada));
		
		menu.addSeparator();
		menu.add(createMenuItem("Vendas Efectuadas", this::viewVendas));
		menu.add(createMenuItem("Vendas de Serviços", this::viewVendaServico));
		menu.add(createMenuItem("Estatistica", this::showEstatistica));
		
		return menu;
	}


	private JMenu createAjudaMenu() {
		JMenu menu = new JMenu("Ajuda");
		menu.add(createMenuItem("Geral", this::about));
		return menu;
	}

	//criação de menu termina aqui

	//metodos para criação item do menu
	private JMenuItem createMenuItem(String text, ActionListener listener) {
		JMenuItem menuItem = new JMenuItem(text);
		menuItem.addActionListener(listener);
		return menuItem;
	}

	//criação do itens do menu termina aqui


	//metodo para criar a barra de ferramentas
	private JToolBar createToolBar() {

		JToolBar toolbar = new JToolBar();

		//toolbar.setBorder(new LineBorder(Tools.red));
		toolbar.setBackground(Tools.white);

		toolbar.add(Tools.createButton("Cliente", 
				Tools.createIcon("/img/icons8_Customer_48px.png", 50), Tools.white, 50, 50, this::showClientesPanel));

		toolbar.addSeparator();

		toolbar.add(Tools.createButton("Fornecedor", 
				Tools.createIcon("/img/icons8_Supplier_48px_1.png", 50), Tools.white, 50, 50, this::showFornecedoresPanel));

		toolbar.addSeparator();

		toolbar.add(Tools.createButton("Funcionarios", 
				Tools.createIcon("/img/icons8_technical_support_48px_2.png", 50), Tools.white, 50, 50, this::showFuncionariosPanel));

		toolbar.addSeparator(new Dimension(30, 30));

		toolbar.add(Tools.createButton("Produtos", 
				Tools.createIcon("/img/icons8_Product_48px.png", 50), Tools.white, 50, 50, this::showProdutosPanel));

		toolbar.addSeparator();

		toolbar.add(Tools.createButton("mais", 
				Tools.createIcon("/img/icons8_More_50px.png", 50), Tools.white, 50, 50, this::showMoreOptions));

		toolbar.addSeparator(new Dimension(30, 30));

		toolbar.addSeparator();

		toolbar.add(Tools.createButton("Vender", 
				Tools.createIcon("/img/icons8_Cash_Register_48px.png", 50), Tools.white, 50, 50, this::vender));

		toolbar.add(Tools.createButton("Estatistica", 
				Tools.createIcon("/img/icons8_Chart_96px.png", 50), Tools.white, 50, 50, this::showEstatistica));

		toolbar.addSeparator();
		toolbar.addSeparator();
		toolbar.addSeparator();

		toolbar.add(Tools.createButton("Sair", 
				Tools.createIcon("/img/icons8_Exit_96px.png", 50), Tools.white, 50, 50, this::logout));



		usernameLabel = Tools.createLabel("", null, Tools.paragraph);
		toolbar.add(usernameLabel);
		return toolbar;
	}


	private JToolBar createOtherToolBar() {

		JToolBar toolbar = new JToolBar();

		//toolbar.setBorder(new LineBorder(Tools.red));
		toolbar.setBackground(Tools.white);

		toolbar.add(Tools.createButton("Cliente", 
				Tools.createIcon("/img/icons8_Customer_48px.png", 50), Tools.white, 50, 50, this::showClientesPanel));

		toolbar.add(Tools.createButton("Vender", 
				Tools.createIcon("/img/icons8_Cash_Register_48px.png", 50), Tools.white, 50, 50, this::vender));

		toolbar.add(Tools.createButton("Encomendas", 
				Tools.createIcon("/img/icons8_Shopping_Basket_48px.png", 50), Tools.white, 50, 50, this::showEncomendasPanel));

		toolbar.addSeparator();
		toolbar.addSeparator();
		toolbar.addSeparator();

		toolbar.add(Tools.createButton("Sair", 
				Tools.createIcon("/img/icons8_Exit_96px.png", 50), Tools.white, 50, 50, this::logout));



		otherUsernameLabel = Tools.createLabel("", null, Tools.paragraph);
		toolbar.add(otherUsernameLabel);
		return toolbar;
	}

	//criar panel principal com os conteudos
	/*aqui estara definido todos o panel que vao estar no cardLayout (:*/
	private JPanel AdminMainContent() {
		cardLayoutMainPanel.setLayout(cl);
		cardLayoutMainPanel.add(FuncionarioPanel(), "funcionarios");
		cardLayoutMainPanel.add(FornecedorePanel(), "fornecedores");
		cardLayoutMainPanel.add(ClientePanel(), "clientes");
		cardLayoutMainPanel.add(ProdutoPanel(), "produtos");
		cardLayoutMainPanel.add(ProdutoAddOptionPanel(), "addProdutoInfo");
		cardLayoutMainPanel.add(produtoCategoriaAddPanel(), "addProdutoCategoria");
		cardLayoutMainPanel.add(produtoTipoAddPanel(), "addProdutoTipo");
		cardLayoutMainPanel.add(produtoAddPanel(), "addProduto");

		cardLayoutMainPanel.add(ProdutoViewOptionPanel(), "viewProdutoInfo");
		cardLayoutMainPanel.add(ProdutoViewPanel(), "viewProdutos");
		cardLayoutMainPanel.add(ProdutoViewCategoriaPanel(), "viewCategorias");
		cardLayoutMainPanel.add(ProdutoViewTipoPanel(), "viewTipoProdutos");
		cardLayoutMainPanel.add(funcionarioAddPanel(), "addFuncionarios");
		cardLayoutMainPanel.add(funcionarioViewPanel(), "viewFuncionarios");
		cardLayoutMainPanel.add(fornecedoreAddPanel(), "addFornecedores");
		cardLayoutMainPanel.add(fornecedoreViewPanel(), "viewFornecedores");
		cardLayoutMainPanel.add(clienteAddPanel(), "addClientes");
		cardLayoutMainPanel.add(clienteViewPanel(), "viewClientes");
		cardLayoutMainPanel.add(morePanel(), "moreOptions");
		cardLayoutMainPanel.add(venderPanel(), "vender");
		cardLayoutMainPanel.add(usersOptionPanel(), "usuarios");
		cardLayoutMainPanel.add(userAddPanel(), "addUsuarios");

		cardLayoutMainPanel.add(userViewPanel(), "viewUsuarios");
		cardLayoutMainPanel.add(vendaOptionPanel(), "vendas");
		cardLayoutMainPanel.add(vendaViewPanel(), "viewVendas");
		cardLayoutMainPanel.add(vendaServicoViewPanel(), "viewVendaServico");

		cardLayoutMainPanel.add(pagamentoOptionPanel(), "pagamentos");
		cardLayoutMainPanel.add(pagamentoViewPanel(), "viewPagamentos");
		cardLayoutMainPanel.add(equipamentoOptionPanel(), "equipamentos");
		//cardLayoutMainPanel.add(equipamentoAddMoreOptionsPanel(), "equipamentoAddMoreOptionsPanel");

		cardLayoutMainPanel.add(equipamentoAddPanel(), "addEquipamentos");
		//cardLayoutMainPanel.add(equipamentoEstadoAddPanel(), "addEquipamentoEstado");

		cardLayoutMainPanel.add(equipamentoViewPanel(), "viewEquipamentos");
		cardLayoutMainPanel.add(equipamentoViewMoreContentPanel(), "viewEquipamentosInfo");
		//cardLayoutMainPanel.add(equipamentoViewEstadoMoreContentPanel(), "viewEquipamentosEstadoInfo");

		cardLayoutMainPanel.add(servicoOptionPanel(), "servicos");
		cardLayoutMainPanel.add(servicoAddPanel(), "addServicos");

		cardLayoutMainPanel.add(servicoViewPanel(), "viewServicos");

		cardLayoutMainPanel.add(encomendaOptionPanel(), "encomendas");
		//cardLayoutMainPanel.add(encomendaAddOptionPanel(), "addEncomendaInfo");
		//cardLayoutMainPanel.add(encomendaEstadoAddPanel(), "addEncomendaEstado");
		cardLayoutMainPanel.add(encomendaAddPanel(), "addEncomendas");
		//cardLayoutMainPanel.add(encomendaViewOptionPanel(), "viewEncomendaInfo");
		cardLayoutMainPanel.add(encomendaViewPanel(), "viewEncomendas");
		//cardLayoutMainPanel.add(encomendaEstadoViewPanel(), "viewEncomendaEstado");

		cardLayoutMainPanel.add(stockOptionPanel(), "stock");
		cardLayoutMainPanel.add(stockProdutoEntradaViewPanel(), "viewStockProdutoEntrada");
		cardLayoutMainPanel.add(produtoEntradaAddPanel(), "addProdutoEntrada");
		cardLayoutMainPanel.add(stockProdutoSaidaViewPanel(), "viewStockProdutoSaida");
		cardLayoutMainPanel.add(stockEncomendaEfectuadaViewPanel(), "viewStockEncomendaEfectuada");


		cardLayoutMainPanel.add(vendaProdutoPanel(), "vendaProduto");
		cardLayoutMainPanel.add(vendaServicoPanel(), "vendaServico");
		cardLayoutMainPanel.add(estatisticaPanel(), "estatistica");

		cardLayoutMainPanel.add(aboutOption(), "about");

		cl.show(cardLayoutMainPanel, "funcionarios");
		return cardLayoutMainPanel;
	}

	/*toda parte de funcionarios panel começa aqui </>:)*/
	//funcionarios panel
	private JPanel FuncionarioPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Funcionarios", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createLabel("", Tools.createIcon("", 50), null));
		panel.add(Tools.createButton(
				"Adicionar funcionarios", Tools.createIcon("/img/icons8_Add_User_Group_Man_Man_96px.png", 50), Tools.lightgrey, 100, 100, this::addFuncionarios));
		panel.add(Tools.createButton(
				"Ver funcionarios", Tools.createIcon("/img/icons8_View_96px.png", 50), Tools.lightgrey, 100, 100, this::viewFuncionarios));

		return panel;
	}

	private JPanel funcionarioAddPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(funcionarioRetornPanel(), BorderLayout.NORTH);
		panel.add(new JScrollPane(funcionarioAddContentPanel()));
		return panel;
	}

	private JPanel funcionarioAddContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Tools.white);
		JLabel bi = Tools.createLabel("BI:", null, Tools.paragraph);
		addComponent(panel, bi, 5, 5, 100, 30);
		addComponent(panel, funcionarioBI, 5, 30, 150, 30);
		JLabel nome = Tools.createLabel("Nome:", null, Tools.paragraph);
		addComponent(panel, nome, 180, 5, 100, 30);
		addComponent(panel, funcionarioNome, 180, 30, 180, 30);
		JLabel sobrenome = Tools.createLabel("Sobrenome:", null, Tools.paragraph);
		addComponent(panel, sobrenome, 400, 5, 100, 30);
		addComponent(panel, funcionarioSobrenome, 400, 30, 180, 30);
		JLabel data = Tools.createLabel("Data de nascimento:", null, Tools.paragraph);
		addComponent(panel, data, 600, 5, 150, 30);
		addComponent(panel, funcionarioNascimento, 600, 30, 150, 30);
		JLabel tel = Tools.createLabel("Tel:", null, Tools.paragraph);
		addComponent(panel, tel, 800, 5, 150, 30);
		addComponent(panel, funcionarioTelefone, 800, 30, 200, 30);
		JLabel cidade = Tools.createLabel("Cidade:", null, Tools.paragraph);
		addComponent(panel, cidade, 5, 80, 150, 30);
		addComponent(panel, funcionarioCidade, 5, 105, 180, 30);
		JLabel municipio = Tools.createLabel("Municipio:", null, Tools.paragraph);
		addComponent(panel, municipio, 200, 80, 150, 30);
		addComponent(panel, funcionarioMunicipio, 200, 105, 220, 30);
		JLabel bairro = Tools.createLabel("Bairro:", null, Tools.paragraph);
		addComponent(panel, bairro, 450, 80, 150, 30);
		addComponent(panel, funcionarioBairro, 450, 105, 220, 30);
		JLabel rua = Tools.createLabel("Rua:", null, Tools.paragraph);
		addComponent(panel, rua, 700, 80, 150, 30);
		addComponent(panel, funcionarioRua, 700, 105, 220, 30);
		JLabel sexo = Tools.createLabel("Sexo:", null, Tools.paragraph);
		addComponent(panel, sexo, 420, 150, 50, 30);
		ButtonGroup group = new ButtonGroup();
		group.add(funcionarioSexoM);
		group.add(funcionarioSexoF);
		addComponent(panel, funcionarioSexoM, 400, 180, 50, 30);
		addComponent(panel, funcionarioSexoF, 450, 180, 50, 30);
		JLabel email = Tools.createLabel("E-mail:", null, Tools.paragraph);
		addComponent(panel, email, 5, 210, 50, 30);
		addComponent(panel, funcionarioEmail, 5, 240, 500, 30);
		JLabel funcao = Tools.createLabel("Função:", null, Tools.paragraph);
		addComponent(panel, funcao, 600, 210, 100, 30);

		ArrayList<String> funcaoList = new ArrayList<String>();
		ctrls.Funcao.SelectAllFuncao(funcaoList);
		for (String funcoes: funcaoList) {
			funcionarioFuncao.addItem(funcoes);
		}

		addComponent(panel, funcionarioFuncao, 600, 240, 200, 30);

		cadastrarFuncionario = Tools.createButton("Cadastrar funcionario", Tools.greenA, 200, 40, this::addFuncionario);
		atualizarFuncionario = Tools.createButton("Atualizar funcionario", Tools.blueA, 200, 40, this::updateFuncionario);
		cadastrarFuncionario.setForeground(Tools.white);
		atualizarFuncionario.setForeground(Tools.white);

		addComponent(panel, cadastrarFuncionario, 730, 350, 200, 40);
		addComponent(panel, atualizarFuncionario, 730, 350, 200, 40);

		return panel;
	}

	private JPanel funcionarioViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(funcionarioRetornPanel(), BorderLayout.NORTH);
		panel.add(funcionarioViewContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todos produtos
	private JPanel funcionarioViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.funcionarioSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(funcionariosTable()));
		panel.add(functionarioUpdatePanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable funcionariosTable() {
		funcionariosSorter = new TableRowSorter<FuncionariosAll>(funcionarios);
		this.FuncionariosTable = new JTable(funcionarios);
		this.FuncionariosTable.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.FuncionariosTable.setRowSorter(funcionariosSorter);
		return this.FuncionariosTable;
	}

	/*private void setColumnWidth(TableColumn col, int width) {
			col.setPreferredWidth(width);
			col.setMinWidth(width);
			col.setMaxWidth(width);
		}*/
	//barra de panel onde vai conter os botoes de atualizar dados dos produtos
	private JPanel functionarioUpdatePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel.setBackground(Tools.white);
		panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
				Tools.lightgrey, 40, 40, this::updateFuncionarioSetDados));
		panel.add(Tools.createButton("Apagar", Tools.createIcon("/img/icons8_Cancel_96px.png", 40), 
				Tools.lightgrey, 40, 40, this::eliminarFuncionario));
		return panel;
	}

	//criar status panel onde vai conter o botao de recuar de funcionarios
	private JPanel funcionarioRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showFuncionariosPanel), BorderLayout.WEST);
		return panel;
	}

	/*a parte do panel de funcionarios termina aqui </>:)*/

	/*toda parte de fornecedores panel começa aqui </>:)*/
	//fornecedores panel
	private JPanel FornecedorePanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Fornecedores", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createLabel("", Tools.createIcon("", 50), null));
		panel.add(Tools.createButton(
				"Adicionar Fornecedores", Tools.createIcon("/img/icons8_Plus_96px.png", 50), Tools.lightgrey, 100, 100, this::addFornecedores));
		panel.add(Tools.createButton(
				"Ver Fornecedores", Tools.createIcon("/img/icons8_View_96px.png", 50), Tools.lightgrey, 100, 100, this::viewFornecedores));

		return panel;
	}

	private JPanel fornecedoreAddPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(fornecedoreRetornPanel(), BorderLayout.NORTH);
		panel.add(new JScrollPane(fornecedoreAddContentPanel()));
		return panel;
	}

	private JPanel fornecedoreAddContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Tools.white);
		JLabel nome = Tools.createLabel("Nome:", null, Tools.paragraph);
		addComponent(panel, nome, 100, 50, 100, 30);
		addComponent(panel, fornecedoreNome, 100, 80, 200, 30);
		JLabel cidade = Tools.createLabel("Cidade:", null, Tools.paragraph);
		addComponent(panel, cidade, 400, 50, 100, 30);
		addComponent(panel, fornecedoreCidade, 400, 80, 200, 30);
		JLabel municipio = Tools.createLabel("Municipio:", null, Tools.paragraph);
		addComponent(panel, municipio, 700, 50, 100, 30);
		addComponent(panel, fornecedoreMunicipio, 700, 80, 200, 30);
		JLabel bairro = Tools.createLabel("Bairro:", null, Tools.paragraph);
		addComponent(panel, bairro, 100, 150, 100, 30);
		addComponent(panel, fornecedoreBairro, 100, 180, 200, 30);
		JLabel pais = Tools.createLabel("Pais:", null, Tools.paragraph);
		addComponent(panel, pais, 340, 150, 100, 30);
		addComponent(panel, fornecedorePais, 340, 180, 140, 30);
		JLabel rua = Tools.createLabel("Rua:", null, Tools.paragraph);
		addComponent(panel, rua, 520, 150, 100, 30);
		addComponent(panel, fornecedoreRua, 520, 180, 140, 30);
		JLabel tel = Tools.createLabel("Telefone:", null, Tools.paragraph);
		addComponent(panel, tel, 700, 150, 100, 30);
		addComponent(panel, fornecedoreTelefone, 700, 180, 200, 30);

		cadastrarFornecedor = Tools.createButton("Adicionar Fornecedor", Tools.greenA, 200, 40, this::addFornecedor);
		atualizarFornecedor = Tools.createButton("Actualizar Fornecedor", Tools.blueA, 200, 40, this::updateFornecedor);
		cadastrarFornecedor.setForeground(Tools.white);
		atualizarFornecedor.setForeground(Tools.white);

		addComponent(panel, cadastrarFornecedor, 700, 350, 200, 40);
		addComponent(panel,  atualizarFornecedor, 700, 350, 200, 40);

		return panel;
	}


	private JPanel fornecedoreViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(fornecedoreRetornPanel(), BorderLayout.NORTH);
		panel.add(fornecedoreViewContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todos produtos
	private JPanel fornecedoreViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.fornecedoreSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(fornecedoresTable()));
		panel.add(fornecedoreUpdatePanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable fornecedoresTable() {
		fornecedoresSorter = new TableRowSorter<FornecedoresAll>(fornecedores);
		this.FornecedoresTable = new JTable(fornecedores);
		this.FornecedoresTable.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.FornecedoresTable.setRowSorter(fornecedoresSorter);
		return this.FornecedoresTable;
	}


	//barra de panel onde vai conter os botoes de atualizar dados dos produtos
	private JPanel fornecedoreUpdatePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel.setBackground(Tools.white);
		panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
				Tools.lightgrey, 40, 40, this::updateFornecedorSetDados));
		panel.add(Tools.createButton("Apagar", Tools.createIcon("/img/icons8_Cancel_96px.png", 40), 
				Tools.lightgrey, 40, 40, this::eliminarFornecedor));
		return panel;
	}

	//criar status panel onde vai conter o botao de recuar de funcionarios
	private JPanel fornecedoreRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showFornecedoresPanel), BorderLayout.WEST);
		return panel;
	}

	/*a parte do panel de fornecedores termina aqui </>:)*/

	/*toda parte do panel do cliente começa aqui </>:)*/
	//clientes panel
	private JPanel ClientePanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Clientes", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createLabel("", Tools.createIcon("", 50), null));
		panel.add(Tools.createButton(
				"Adicionar clientes", Tools.createIcon("/img/icons8_Add_User_Male_96px.png", 50), Tools.lightgrey, 100, 100, this::addClientes));
		panel.add(Tools.createButton(
				"Ver clientes", Tools.createIcon("/img/icons8_View_96px.png", 50), Tools.lightgrey, 100, 100, this::viewClientes));

		return panel;
	}

	private JPanel clienteAddPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(clienteRetornPanel(), BorderLayout.NORTH);
		panel.add(new JScrollPane(clienteAddContentPanel()));
		return panel;
	}

	private JPanel clienteAddContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Tools.white);
		JLabel nome = Tools.createLabel("Nome:", null, Tools.paragraph);
		addComponent(panel, nome, 100, 50, 100, 30);
		addComponent(panel, clienteNome, 100, 80, 200, 30);
		JLabel cidade = Tools.createLabel("Cidade:", null, Tools.paragraph);
		addComponent(panel, cidade, 400, 50, 100, 30);
		addComponent(panel, clienteCidade, 400, 80, 200, 30);
		JLabel municipio = Tools.createLabel("Municipio:", null, Tools.paragraph);
		addComponent(panel, municipio, 700, 50, 100, 30);
		addComponent(panel, clienteMunicipio, 700, 80, 200, 30);
		JLabel bairro = Tools.createLabel("Bairro:", null, Tools.paragraph);
		JLabel sexo = Tools.createLabel("Sexo:", null, Tools.paragraph);
		addComponent(panel, sexo, 500, 130, 50, 30);
		ButtonGroup group = new ButtonGroup();
		group.add(clienteSexoM);
		group.add(clienteSexoF);
		addComponent(panel, clienteSexoM, 460, 160, 50, 30);
		addComponent(panel, clienteSexoF, 520, 160, 50, 30);

		addComponent(panel, bairro, 100, 220, 100, 30);
		addComponent(panel, clienteBairro, 100, 250, 200, 30);
		JLabel pais = Tools.createLabel("Pais:", null, Tools.paragraph);
		addComponent(panel, pais, 340, 220, 100, 30);
		addComponent(panel, clientePais, 340, 250, 140, 30);
		JLabel rua = Tools.createLabel("Rua:", null, Tools.paragraph);
		addComponent(panel, rua, 520, 220, 100, 30);
		addComponent(panel, clienteRua, 520, 250, 140, 30);
		JLabel tel = Tools.createLabel("Telefone:", null, Tools.paragraph);
		addComponent(panel, tel, 700, 220, 100, 30);
		addComponent(panel, clienteTelefone, 700, 250, 200, 30);

		cadastrarCliente = Tools.createButton("Cadastrar Cliente", Tools.greenA, 200, 40, this::addCliente);
		atualizarCliente = Tools.createButton("Atualizar Cliente", Tools.blueA, 200, 40, this::updateCliente);
		cadastrarCliente.setForeground(Tools.white);
		atualizarCliente.setForeground(Tools.white);

		addComponent(panel, cadastrarCliente, 700, 350, 200, 40);
		addComponent(panel, atualizarCliente, 700, 350, 200, 40);

		return panel;
	}

	private JPanel clienteViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(clienteRetornPanel(), BorderLayout.NORTH);
		panel.add(clienteViewContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todos produtos
	private JPanel clienteViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.clienteSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(clientesTable()));
		panel.add(clienteUpdatePanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable clientesTable() {
		clientesSorter = new TableRowSorter<ClienteAll>(clientes);
		this.ClientesTable = new JTable(clientes);
		this.ClientesTable.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.ClientesTable.setRowSorter(clientesSorter);
		return this.ClientesTable;
	}


	//barra de panel onde vai conter os botoes de atualizar dados dos produtos
	private JPanel clienteUpdatePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel.setBackground(Tools.white);
		panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
				Tools.lightgrey, 40, 40, this::updateClienteSetDados));
		panel.add(Tools.createButton("Apagar", Tools.createIcon("/img/icons8_Cancel_96px.png", 40), 
				Tools.lightgrey, 40, 40, this::eliminarCliente));
		return panel;
	}

	//criar status panel onde vai conter o botao de recuar de funcionarios
	private JPanel clienteRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showClientesPanel), BorderLayout.WEST);
		return panel;
	}

	/*toda parte do panel cliente termina aqui </>:)*/

	/*toda parte de produtos panel começa aqui </>:)*/
	//produtos panel
	private JPanel ProdutoPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Produtos", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createLabel("", Tools.createIcon("", 50), null));
		panel.add(Tools.createButton(
				"Adicionar Produtos Info", Tools.createIcon("/img/icons8_Plus_96px.png", 50), Tools.lightgrey, 100, 100, this::produtoAddOptionPanel));
		panel.add(Tools.createButton(
				"Ver Produtos info", Tools.createIcon("/img/icons8_View_96px.png", 50), Tools.lightgrey, 100, 100, this::ProdutoViewOptionPanel));

		return panel;
	}

	//metodo que vai ter todas as opcoes de insercao de produtos
	private JPanel ProdutoAddOptionPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(ProdutoRetornPanel(), BorderLayout.NORTH);
		panel.add(ProdutoAddOptionShowPanel());
		return panel;
	}


	//metodo que mostra todas opcoes de inserção de produtos
	private JPanel ProdutoAddOptionShowPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Adicionar Produtos", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createButton(
				"Adicionar Categoria", Tools.createIcon("/img/icons8_Categorize_96px.png", 50), Tools.lightgrey, 100, 100, this::addProdutoCategoria));
		panel.add(Tools.createButton(
				"adicionar produtos", Tools.createIcon("/img/icons8_Plus_96px.png", 50), Tools.lightgrey, 100, 100, this::addProduto));
		panel.add(Tools.createButton(
				"adicionar o Tipo do produto", Tools.createIcon("/img/icons8_Brick_96px.png", 50), Tools.lightgrey, 100, 100, this::addProdutoTipo));

		return panel;
	}

	private JPanel produtoCategoriaAddPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(ProdutoAddContentRetornPanel(), BorderLayout.NORTH);
		panel.add(new JScrollPane(produtoCategoriaAddContentPanel()));
		return panel;
	}

	private JPanel produtoCategoriaAddContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Tools.white);
		JLabel nome = Tools.createLabel("Nome:", null, Tools.paragraph);
		addComponent(panel, nome, 420, 100, 100, 30);
		addComponent(panel, produtoCategoriaNome, 420, 130, 200, 30);

		cadastrarProdutoCategoria = Tools.createButton("Cadastrar categoria de produtos", Tools.greenA, 200, 40, this::addProdutoCategoriaInsert);
		atualizarProdutoCategoria = Tools.createButton("atualizar categoria de produtos", Tools.blueA, 200, 40, this::updateProdutoCategoria);
		cadastrarProdutoCategoria.setForeground(Tools.white);
		atualizarProdutoCategoria.setForeground(Tools.white);

		addComponent(panel, cadastrarProdutoCategoria, 730, 350, 200, 40);
		addComponent(panel, atualizarProdutoCategoria, 730, 350, 200, 40);

		return panel;
	}


	private JPanel produtoTipoAddPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(ProdutoAddContentRetornPanel(), BorderLayout.NORTH);
		panel.add(new JScrollPane(produtoTipoAddContentPanel()));
		return panel;
	}

	private JPanel produtoTipoAddContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Tools.white);
		JLabel nome = Tools.createLabel("Nome:", null, Tools.paragraph);
		addComponent(panel, nome, 420, 100, 100, 30);
		addComponent(panel, produtoTipoNome, 420, 130, 200, 30);

		cadastrarProdutoTipo = Tools.createButton("Cadastrar tipo de produto", Tools.greenA, 200, 40, this::addProdutoTipoProduto);
		atualizarProdutoTipo = Tools.createButton("atualizar tipo de produto", Tools.blueA, 200, 40, this::updateProdutoTipo);
		cadastrarProdutoTipo.setForeground(Tools.white);
		atualizarProdutoTipo.setForeground(Tools.white);


		addComponent(panel, cadastrarProdutoTipo, 730, 350, 200, 40);
		addComponent(panel, atualizarProdutoTipo, 730, 350, 200, 40);

		return panel;
	}

	private JPanel produtoAddPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(ProdutoAddContentRetornPanel(), BorderLayout.NORTH);
		panel.add(new JScrollPane(produtoAddContentPanel()));
		return panel;
	}

	private JPanel produtoAddContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Tools.white);
		JLabel preco = Tools.createLabel("Preço:", null, Tools.paragraph);
		addComponent(panel, preco, 250, 100, 100, 30);
		addComponent(panel, produtoPreco, 250, 130, 200, 30);
		JLabel quantidade = Tools.createLabel("Quantidade:", null, Tools.paragraph);
		addComponent(panel, quantidade, 600, 100, 100, 30);
		this.produtoQuantidade.setEnabled(false);
		this.produtoQuantidade.setText("0");
		addComponent(panel, produtoQuantidade, 600, 130, 200, 30);
		JLabel categoria = Tools.createLabel("Categoria:", null, Tools.paragraph);
		addComponent(panel, categoria, 250, 180, 100, 30);

		ArrayList<String> categoriaList = new ArrayList<String>();
		ctrls.Produto.SelectAllCategoria(categoriaList);
		for (String categorias: categoriaList) {
			produtoCategoria.addItem(categorias);
		}

		addComponent(panel, produtoCategoria, 250, 210, 200, 30);
		JLabel tipo = Tools.createLabel("Tipo:", null, Tools.paragraph);
		addComponent(panel, tipo, 600, 180, 100, 30);

		ArrayList<String> tipoList = new ArrayList<String>();
		ctrls.Produto.SelectAllTipo(tipoList);
		for (String tipos: tipoList) {
			produtoTipo.addItem(tipos);
		}

		addComponent(panel, produtoTipo, 600, 210, 200, 30);

		cadastrarProduto = Tools.createButton("Cadastrar produto", Tools.greenA, 200, 40, this::addProdutoInsert);
		atualizarProduto = Tools.createButton("Atualizar produto", Tools.blueA, 200, 40, this::updateProduto);
		cadastrarProduto.setForeground(Tools.white);
		atualizarProduto.setForeground(Tools.white);

		addComponent(panel, cadastrarProduto, 600, 350, 200, 40);
		addComponent(panel, atualizarProduto, 600, 350, 200, 40);

		return panel;
	}


	//metodo que vai ter todas as opcoes de insercao de produtos
	private JPanel ProdutoViewOptionPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(ProdutoRetornPanel(), BorderLayout.NORTH);
		panel.add(ProdutoViewOptionShowPanel());
		return panel;
	}


	//metodo que mostra todas opcoes de inserção de produtos
	private JPanel ProdutoViewOptionShowPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("ver produtos", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createButton(
				"Visualizar Categoria", Tools.createIcon("/img/icons8_Categorize_96px.png", 50), Tools.lightgrey, 100, 100, this::showCategorias));
		panel.add(Tools.createButton(
				"Visualizar produtos", Tools.createIcon("/img/icons8_Product_48px.png", 50), Tools.lightgrey, 100, 100, this::viewProdutos));
		panel.add(Tools.createButton(
				"visualizar o Tipo do produto", Tools.createIcon("/img/icons8_Brick_96px.png", 50), Tools.lightgrey, 100, 100, this::showTipoProduto));

		return panel;
	}

	private JPanel ProdutoViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(ProdutoViewContentRetornPanel(), BorderLayout.NORTH);
		panel.add(ProdutoViewContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todos produtos
	private JPanel ProdutoViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.produtoSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(produtosTable()));
		panel.add(ProdutoUpdatePanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable produtosTable() {
		produtosSorter = new TableRowSorter<ProdutosAll>(produtos);
		this.ProdutosTable = new JTable(produtos);
		this.ProdutosTable.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.ProdutosTable.setRowSorter(produtosSorter);
		return this.ProdutosTable;
	}


	//barra de panel onde vai conter os botoes de atualizar dados dos produtos
	private JPanel ProdutoUpdatePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel.setBackground(Tools.white);
		panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
				Tools.lightgrey, 40, 40, this::updateProdutoSetDados));
		panel.add(Tools.createButton("Apagar", Tools.createIcon("/img/icons8_Cancel_96px.png", 40), 
				Tools.lightgrey, 40, 40, this::eliminarProduto));
		return panel;
	}

	//parte de visualizar a categoria do produto mesmo codico com a de visualizar os produtos
	private JPanel ProdutoViewCategoriaPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(ProdutoViewContentRetornPanel(), BorderLayout.NORTH);
		panel.add(ProdutoViewCategoriaContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todas as categorias dos produtos
	private JPanel ProdutoViewCategoriaContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.categoriaSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(produtoCategoriasTable()));
		panel.add(ProdutoUpdateCategoriaPanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable produtoCategoriasTable() {
		produtosCategoriaSorter = new TableRowSorter<CategoriasAll>(categorias);
		this.ProdutoCategoriasTable = new JTable(categorias);
		this.ProdutoCategoriasTable.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.ProdutoCategoriasTable.setRowSorter(produtosCategoriaSorter);
		return this.ProdutoCategoriasTable;
	}

	//barra de panel onde vai conter os botoes de atualizar dados das categorias do produtos
	private JPanel ProdutoUpdateCategoriaPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel.setBackground(Tools.white);
		panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
				Tools.lightgrey, 40, 40, this::updateProdutoCategoriaSetDados));
		panel.add(Tools.createButton("Apagar", Tools.createIcon("/img/icons8_Cancel_96px.png", 40), 
				Tools.lightgrey, 40, 40, this::eliminarProdutoCategoria));
		return panel;
	}
	/**/

	//parte de visualizar a categoria do produto mesmo codico com a de visualizar os produtos
	private JPanel ProdutoViewTipoPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(ProdutoViewContentRetornPanel(), BorderLayout.NORTH);
		panel.add(ProdutoViewTipoContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todas as categorias dos produtos
	private JPanel ProdutoViewTipoContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.tipoProdutoSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(produtoTiposTable()));
		panel.add(ProdutoUpdateTipoPanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable produtoTiposTable() {
		produtosTipoSorter = new TableRowSorter<TipoProdutosAll>(tipos);
		this.ProdutoTiposTable = new JTable(tipos);
		this.ProdutoTiposTable.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.ProdutoTiposTable.setRowSorter(produtosTipoSorter);
		return this.ProdutoTiposTable;
	}


	//barra de panel onde vai conter os botoes de atualizar dados das categorias do produtos
	private JPanel ProdutoUpdateTipoPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel.setBackground(Tools.white);
		panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
				Tools.lightgrey, 40, 40, this::updateProdutoTipoSetDados));
		panel.add(Tools.createButton("Apagar", Tools.createIcon("/img/icons8_Cancel_96px.png", 40), 
				Tools.lightgrey, 40, 40, this::eliminarProdutoTipo));
		return panel;
	}
	/**/

	//criar status panel onde vai conter o botao de recuar
	private JPanel ProdutoRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showProdutosPanel), BorderLayout.WEST);
		return panel;
	}


	private JPanel ProdutoAddContentRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::produtoAddOptionPanel), BorderLayout.WEST);
		return panel;
	}

	private JPanel ProdutoViewContentRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::ProdutoViewOptionPanel), BorderLayout.WEST);
		return panel;
	}



	/*toda parte de produtos termina aqui </>:)*/

	/*toda parte de mais opções  começa aqui </>:)*/

	private JPanel morePanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Mais Opções", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createButton(
				"usuarios do sistema", Tools.createIcon("/img/icons8_User_48px.png", 40), Tools.lightgrey, 100, 100, this::showUsersPanel));
		panel.add(Tools.createButton(
				"Vendas", Tools.createIcon("/img/icons8_Sell_48px.png", 40), Tools.lightgrey, 100, 100, this::showVendasPanel));
		panel.add(Tools.createButton(
				"Pagamentos", Tools.createIcon("/img/icons8_Payment_History_48px.png", 40), Tools.lightgrey, 100, 100, this::showPagamentosPanel));
		panel.add(Tools.createButton(
				"nossos equipamentos", Tools.createIcon("/img/icons8_Maintenance_96px_1.png", 40), Tools.lightgrey, 100, 100, this::showEquipamentosPanel));
		panel.add(Tools.createButton(
				"nossos serviços", Tools.createIcon("/img/icons8_Services_48px.png", 40), Tools.lightgrey, 100, 100, this::showServicosPanel));
		/**/
		panel.add(Tools.createButton(
				"Encomendas", Tools.createIcon("/img/icons8_Shopping_Basket_48px.png", 40), Tools.lightgrey, 100, 100, this::showEncomendasPanel));
		panel.add(Tools.createButton(
				"Meu Perfil", Tools.createIcon("/img/icons8_Profile_48px.png", 40), Tools.lightgrey, 100, 100, null));
		panel.add(Tools.createButton(
				"Stock", Tools.createIcon("/img/icons8_Sell_Stock_48px.png", 40), Tools.lightgrey, 100, 100, this::showStockPanel));

		return panel;
	}

	/*toda parte de mais opções  termina aqui </>:)*/

	/*a parte de vender começa aqui </> :)*/
	private JPanel venderPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Vender", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createButton(
				"vender serviços", Tools.createIcon("/img/icons8_Cash__48px.png", 40), Tools.lightgrey, 100, 100, this::vendaServico));
		panel.add(Tools.createButton(
				"vender produtos", Tools.createIcon("/img/icons8_Cash__48px.png", 40), Tools.lightgrey, 100, 100, this::vendaProduto));

		return panel;
	}


	private JPanel vendaProdutoPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(venderOptionContentRetornPanel(), BorderLayout.NORTH);
		panel.add(venderProdutoContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todas as categorias dos produtos
	private JPanel venderProdutoContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		//panel.add(createSearchFieldPanel(this.venderProdutoSearchField), BorderLayout.NORTH);
		panel.add(venderProdutoContentBottomPanel());
		return panel;
	}


	private JPanel venderProdutoContentBottomPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Tools.white);
		this.itemSelecionadoTable = new JTable(this.venderProdutos);

		JLabel total = Tools.createLabel("Total:", null, Tools.paragraph); 
		JLabel totalValue = Tools.createLabel("0", null, Tools.paragraph);
		JLabel qtStock = Tools.createLabel("0", null, Tools.paragraph);
		JLabel preco = Tools.createLabel("0kz", null, Tools.paragraph);
		
		JLabel tPagamento = Tools.createLabel("Tipo Pagamento", null, Tools.paragraph);
		JLabel valorPagar = Tools.createLabel("Valor entregue:", null, Tools.paragraph);
		JLabel troco = Tools.createLabel("Troco:", null, Tools.paragraph);
		JLabel trocoValor = Tools.createLabel("0kz", null, Tools.paragraph);
		JLabel valorCartao = Tools.createLabel("Valor entregue a cartão:", null, Tools.paragraph);
		JTextField valorPagarField = new JTextField();
		JTextField valorPagarCartaoField = new JTextField();
		Tools.alignRight(totalValue);
		Tools.alignRight(qtStock);
		Tools.alignRight(preco);
		Tools.alignRight(vendaNumber);
		Tools.alignRight(trocoValor);

		troco.setVisible(false);
		trocoValor.setVisible(false);

		//botao pra poder adicionar um produto a table 
		JButton btnAdicionar = Tools.createButton("Adicionar", Tools.blueA, 100, 30, (e) ->{
			int qtStockValue = Integer.parseInt(qtStock.getText());
			if (((String)this.venderProduto.getSelectedItem()).equals("")) {
				JOptionPane.showMessageDialog(this, "<html><p color='red'>Por favor selecione um produto</p>" + "</html>");
			}else if (this.venderQuantidade.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "<html><p color='red'>Por favor insere a quantidade</p>" + "</html>");

			}else {
				int qtEntered = Integer.parseInt(this.venderQuantidade.getText());
				if (qtStockValue < qtEntered) {
					JOptionPane.showMessageDialog(this, "<html><p color='red'>Quantidade inexistente em estoque!</p>" + "</html>");
				}else {


					if (Integer.parseInt(this.venderQuantidade.getText()) <= 0) {
						JOptionPane.showMessageDialog(this, "<html><p color='red'>Quantidade inaceitável!</p>" + "</html>");
					}else {


						int value = Integer.parseInt(this.venderQuantidade.getText());
						String pay = preco.getText();
						float a = Float.parseFloat(pay.substring(0, pay.length()-2))*value;


						if (this.venderProdutos.exists((String)this.venderProduto.getSelectedItem())) {
							for (int i = 0; i < this.itemSelecionadoTable.getRowCount(); i++) {
								this.venderProdutos.appendValue(i, (String)this.venderProduto.getSelectedItem(), value, a);
							}
						}else {
							this.venderProdutos.addRow(
									value, (String)this.venderProduto.getSelectedItem(),  a);
						}


						float b = Float.parseFloat(totalValue.getText().toString().endsWith("kz") ? 
								totalValue.getText().toString().substring(0, totalValue.getText().length() - 2) : totalValue.getText().toString());
						float c = b + a;
						qtStock.setText("" + (Integer.parseInt(qtStock.getText()) - qtEntered));
						totalValue.setText("" + c + "kz");

						this.venderProdutos.fireTableDataChanged();
					}

				}
			}
		});
		btnAdicionar.setForeground(Tools.white);

		//botao pra poder limpar todas vendas ou produtos adicionado a table
		JButton btnLimpar = Tools.createButton("Cancelar", Tools.redA, 100, 30, (e) ->{
			resetVenda(totalValue, troco, trocoValor, valorPagarField);
			valorPagar.setVisible(false);
			valorPagarField.setVisible(false);
			valorCartao.setVisible(false);
			valorPagarCartaoField.setVisible(false);
		});

		btnLimpar.setForeground(Tools.white);

		JButton btnVender = Tools.createButton("Vender", Tools.greenA, 100, 30, (e)->{
			if (itemSelecionadoTable.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "Por favor adicione um produto");
			}else {
				
				String name = JOptionPane.showInputDialog(null, "Nome do cliente: ");
				
				if (name.equalsIgnoreCase(null) || name.isEmpty()) {
					return;
				}

				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				String date = df.format(new java.util.Date());

				float totalV = Float.parseFloat(totalValue.getText().toString().endsWith("kz") ? 
						totalValue.getText().toString().substring(0, totalValue.getText().toString().length() - 2) : totalValue.getText().toString());

				Object[][] itens = new Object[itemSelecionadoTable.getRowCount()][3];
				for (int i = 0; i < itemSelecionadoTable.getRowCount(); i++) {
					for (int j = 0; j < 3; j++) {
						itens[i][j] =  itemSelecionadoTable.getValueAt(i, j);
					}
				}

				faturaMazeda.addItens(itens.length, itens);

				if (!((String)this.tipoPagamento.getSelectedItem()).equals("Dinheiro")) {
					if (((String)this.tipoPagamento.getSelectedItem()).equals("Cartão")) {
						//efectua a venda
						int tipoPagamentoId = ctrls.TipoPagamento.SelectTipoPagamentoId((String)this.tipoPagamento.getSelectedItem());
						int clienteId = ctrls.Cliente.SelectClienteId((String)this.venderCliente.getSelectedItem());

						String produtoCategorias = venderProdutos.AllCategorias();
						String produtoQuantidade = venderProdutos.AllQuantidades();

						ctrls.Vender.vender(tipoPagamentoId, 0, totalV, clienteId, this.BIfuncionario);
						ctrls.Vender.produtosVendidos(produtoCategorias, produtoQuantidade);


						try {
							faturaMazeda.fatura(name, (String)this.tipoPagamento.getSelectedItem(),
									Integer.parseInt(vendaNumber.getText()), totalV, totalV, 0);
							ctrls.PrintPdf.executeFatura();
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage());
						}

						pagamentos.addRow(ctrls.Pagamento.SelectLastPagamentoId(), (String)this.tipoPagamento.getSelectedItem(), 
								totalV, 0, totalV);
						vendas.addRow(ctrls.Venda.SelectLastVendaId(), ctrls.Funcionario.selectFuncionarioNome(this.BIfuncionario), (String)this.venderCliente.getSelectedItem(), 
								totalV, date.replace('/', '-'));

						for (int i = 0; i < itens.length; i++) {
							if (produtosSaida.exists((String)itens[i][0], (int)itens[i][1])) {
								produtosSaida.appendValue((String)itens[i][0], (int)itens[i][1]);
							}else {
								produtosSaida.addRow((String)itens[i][0], (int)itens[i][1]);
							}
						}

						updateStockOnSale();
						
						pagamentos.fireTableDataChanged();
						vendas.fireTableDataChanged();
						produtosSaida.fireTableDataChanged();
						

						vendaNumber.setText("" + ((Integer.parseInt(vendaNumber.getText())) + 1));
						JOptionPane.showMessageDialog(this, "Venda efectuada com sucesso!");
						resetVenda(totalValue, troco, trocoValor, valorPagarField);
					}else {

						//venda a misto

						if (valorPagarField.getText().isEmpty() || valorPagarCartaoField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(this, "Por favor Insira o valor entregue pelo cliente ou o valor a cartão");
						}else {
							float valorMistoDinheiro = Float.parseFloat(valorPagarField.getText());
							float valorMistoCartao = Float.parseFloat(valorPagarCartaoField.getText());
							float valorTotalMisto = valorMistoDinheiro + valorMistoCartao; 

							if (valorTotalMisto < totalV) {
								JOptionPane.showMessageDialog(this, "O valor pago é insuficiente para efectuar a venda");
							}else{

								troco.setVisible(true);
								trocoValor.setVisible(true);

								int tipoPagamentoId = ctrls.TipoPagamento.SelectTipoPagamentoId((String)this.tipoPagamento.getSelectedItem());
								int clienteId = ctrls.Cliente.SelectClienteId((String)this.venderCliente.getSelectedItem());

								String produtoCategorias = venderProdutos.AllCategorias();
								String produtoQuantidade = venderProdutos.AllQuantidades();

								float trocoV = valorTotalMisto - totalV;

								valorMistoDinheiro = valorMistoDinheiro - trocoV;

								ctrls.Vender.vender(tipoPagamentoId, valorMistoDinheiro, valorMistoCartao, clienteId, this.BIfuncionario);
								ctrls.Vender.produtosVendidos(produtoCategorias, produtoQuantidade);

								try {
									faturaMazeda.fatura(name, (String)this.tipoPagamento.getSelectedItem(),
											Integer.parseInt(vendaNumber.getText()), totalV, (valorTotalMisto),
											((valorTotalMisto) - totalV));
									ctrls.PrintPdf.executeFatura();
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, e2.getMessage());
								}

								pagamentos.addRow(ctrls.Pagamento.SelectLastPagamentoId(), (String)this.tipoPagamento.getSelectedItem(), 
										totalV, valorMistoDinheiro, valorMistoCartao);
								vendas.addRow(ctrls.Venda.SelectLastVendaId(), ctrls.Funcionario.selectFuncionarioNome(this.BIfuncionario), (String)this.venderCliente.getSelectedItem(), 
										totalV, date.replace('/', '-'));

								for (int i = 0; i < itens.length; i++) {
									if (produtosSaida.exists((String)itens[i][0], (int)itens[i][1])) {
										produtosSaida.appendValue((String)itens[i][0], (int)itens[i][1]);
									}else {
										produtosSaida.addRow((String)itens[i][0], (int)itens[i][1]);
									}
								}
								
								updateStockOnSale();

								pagamentos.fireTableDataChanged();
								vendas.fireTableDataChanged();
								produtosSaida.fireTableDataChanged();

								vendaNumber.setText("" + ((Integer.parseInt(vendaNumber.getText())) + 1));
								JOptionPane.showMessageDialog(this, "Venda efectuada com sucesso!");
								//resetVenda(totalValue, troco, trocoValor, valorPagarField);
								trocoValor.setText("" + (trocoV) + "kz");
							}


						}
					}

				}else {

					//venda a dinheiro

					if (valorPagarField.getText().equals("")) {
						JOptionPane.showMessageDialog(this, "Por favor Insira o valor entregue pelo cliente");
					}else {

						float valorEntrege = Float.parseFloat(valorPagarField.getText());

						if (valorEntrege < totalV) {
							JOptionPane.showMessageDialog(this, "Valor entregue insufieciente para efectuar a venda");
						}else {
							troco.setVisible(true);
							trocoValor.setVisible(true);
							int tipoPagamentoId = ctrls.TipoPagamento.SelectTipoPagamentoId((String)this.tipoPagamento.getSelectedItem());
							int clienteId = ctrls.Cliente.SelectClienteId((String)this.venderCliente.getSelectedItem());

							String produtoCategorias = venderProdutos.AllCategorias();
							String produtoQuantidade = venderProdutos.AllQuantidades();

							ctrls.Vender.vender(tipoPagamentoId, totalV, 0, clienteId, this.BIfuncionario);
							ctrls.Vender.produtosVendidos(produtoCategorias, produtoQuantidade);

							try {
								faturaMazeda.fatura(name, (String)this.tipoPagamento.getSelectedItem(),
										Integer.parseInt(vendaNumber.getText()), totalV, valorEntrege, (valorEntrege - totalV));
								ctrls.PrintPdf.executeFatura();
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, e2.getMessage());
							}


							pagamentos.addRow(ctrls.Pagamento.SelectLastPagamentoId(), (String)this.tipoPagamento.getSelectedItem(), 
									totalV, totalV, 0);
							vendas.addRow(ctrls.Venda.SelectLastVendaId(), ctrls.Funcionario.selectFuncionarioNome(this.BIfuncionario), (String)this.venderCliente.getSelectedItem(), 
									totalV, date.replace('/', '-'));

							for (int i = 0; i < itens.length; i++) {
								if (produtosSaida.exists((String)itens[i][0], (int)itens[i][1])) {
									produtosSaida.appendValue((String)itens[i][0], (int)itens[i][1]);
								}else {
									produtosSaida.addRow((String)itens[i][0], (int)itens[i][1]);
								}
							}
							

							updateStockOnSale();

							pagamentos.fireTableDataChanged();
							vendas.fireTableDataChanged();
							produtosSaida.fireTableDataChanged();

							vendaNumber.setText("" + ((Integer.parseInt(vendaNumber.getText())) + 1));
							JOptionPane.showMessageDialog(this, "Venda efectuada com sucesso!");
							trocoValor.setText("" + (valorEntrege - totalV) + "kz");
							//resetVenda(totalValue, troco, trocoValor, valorPagarField);
						}

					}
				}
				vendasCount.setText("" + ((Integer.parseInt(vendasCount.getText()) + 1)));
			}
		});
		btnVender.setForeground(Tools.white);

		addComponent(panel, Tools.createLabel("Venda de Produtos", null, Tools.header), 10, 10, 300, 30);
		addComponent(panel, Tools.createLabel("Numero da venda:", null, Tools.paragraph), 10, 50, 300, 30);

		vendaNumber.setText("" + (ctrls.Venda.SelectLastVendaId() + 1));

		addComponent(panel, vendaNumber, 10, 50, 300, 30);
		addComponent(panel, Tools.createLabel("Selecionar Cliente", null, Tools.paragraph), 10, 80, 300, 30);

		ArrayList<String> allClientes = new ArrayList<String>();
		ctrls.Cliente.SelectAllClientes(allClientes);

		for (String cliente : allClientes) {
			this.venderCliente.addItem(cliente);
		}

		addComponent(panel, this.venderCliente, 10, 110, 300, 30);
		addComponent(panel, Tools.createLabel("Produto", null, Tools.paragraph), 10, 140, 300, 30);

		ArrayList<String> allCategoria = new ArrayList<String>();
		ctrls.Produto.SelectAllCategoriaExistingOnProduto(allCategoria);

		this.venderProduto.addItem("");

		for (String categoria : allCategoria) {
			this.venderProduto.addItem(categoria);
		}


		this.venderProduto.addItemListener((e) ->{
			int quantidade = ctrls.Produto.selectQuantidade((String)this.venderProduto.getSelectedItem());
			float precoProduto = ctrls.Produto.selectPreco((String)this.venderProduto.getSelectedItem());

			if (this.itemSelecionadoTable.getRowCount() > 0) {
				int tableQt = venderProdutos.returnIfExists((String)this.venderProduto.getSelectedItem());
				qtStock.setText("" + (quantidade - tableQt));
			}else {
				qtStock.setText("" + quantidade);
			}

			preco.setText("" + precoProduto + "kz");
		});

		/*tipo de pagamento JComboBox*/

		addComponent(panel, this.venderProduto, 10, 170, 300, 30);
		addComponent(panel, Tools.createLabel("Quantidade em stock: ", null, Tools.paragraph), 10, 200, 300, 30);
		addComponent(panel, qtStock, 10, 200, 300, 30);
		addComponent(panel, Tools.createLabel("Preço: ", null, Tools.paragraph), 10, 230, 300, 30);
		addComponent(panel, preco, 10, 230, 300, 30);
		addComponent(panel, Tools.createLabel("Quantidade: ", null, Tools.paragraph), 10, 260, 300, 30);
		addComponent(panel, this.venderQuantidade, 10, 300, 300, 30);
		addComponent(panel, btnAdicionar, 10, 350, 100, 30);
		addComponent(panel, btnLimpar, 120, 350, 100, 30);
		addComponent(panel, btnVender, ((this.getWidth()) - 130), 380, 100, 30);
		addComponent(panel, new JScrollPane(this.itemSelecionadoTable), ((this.getWidth() / 2) + 20), 10, ((this.getWidth() / 2) - 50), (this.getHeight() / 2));
		addComponent(panel, tPagamento, 570, (this.getHeight() / 2) + 10, 130, 30);
		addComponent(panel, valorCartao, 570, 350, 200, 30);
		addComponent(panel, valorPagarCartaoField, 570, 380, 230, 30);

		ArrayList<String> allTipoPagamentos = new ArrayList<String>();
		ctrls.TipoPagamento.selectAllTipoPagamento(allTipoPagamentos);

		for (String tipoPagamento : allTipoPagamentos) {
			this.tipoPagamento.addItem(tipoPagamento);
		}

		this.tipoPagamento.addItemListener((e) ->{
			if (this.tipoPagamento.getSelectedItem().equals("Cartão")) {
				valorPagar.setVisible(false);
				valorPagarField.setVisible(false);
				valorCartao.setVisible(false);
				valorPagarCartaoField.setVisible(false);
			}else if(this.tipoPagamento.getSelectedItem().equals("Dinheiro")) {
				valorPagar.setVisible(true);
				valorPagarField.setVisible(true);
				valorCartao.setVisible(false);
				valorPagarCartaoField.setVisible(false);
			}else if(this.tipoPagamento.getSelectedItem().equals("Misto")) {
				valorPagar.setVisible(true);
				valorPagarField.setVisible(true);
				valorCartao.setVisible(true);
				valorPagarCartaoField.setVisible(true);
			}
		});

		valorPagar.setVisible(false);
		valorPagarField.setVisible(false);
		valorCartao.setVisible(false);
		valorPagarCartaoField.setVisible(false);

		addComponent(panel, this.tipoPagamento, 570, 320, 110, 30);
		addComponent(panel, valorPagar, 700, (this.getHeight() / 2) + 10, 130, 30);
		addComponent(panel, valorPagarField, 700, 320, 100, 30);
		addComponent(panel, troco, ((this.getWidth()) - 200), (this.getHeight() / 2) + 60, 170, 30);
		addComponent(panel, trocoValor, ((this.getWidth()) - 200), (this.getHeight() / 2) + 60, 170, 30);
		addComponent(panel, total, (this.getWidth()) - 200, (this.getHeight() / 2) + 10, 170, 30);
		addComponent(panel, totalValue, (this.getWidth()) - 200, (this.getHeight() / 2) + 10, 170, 30);

		/*this.venderProdutoTable.getSelectionModel().addListSelectionListener((e) -> {

			});*/



		return panel;
	}
	
	private void updateStockOnSale() {
		int id, productId, quantidade;
		for (int i = 0; i < itemSelecionadoTable.getRowCount(); i++) {
			id = ctrls.Produto.SelectCategoriaId((String)itemSelecionadoTable.getValueAt(i, 0));
			productId = ctrls.Produto.SelectProdutoId(id);
			quantidade = (int)itemSelecionadoTable.getValueAt(i, 1);
			produtos.decreValue(productId, quantidade);
		}
	}

	private void resetVenda(JLabel totalValue, JLabel troco, JLabel trocoValor, JTextField valorPagarField) {
		this.venderProduto.setSelectedItem((Object)"");
		this.venderQuantidade.setText("");
		this.venderProdutos.removeAll();
		totalValue.setText("0");
		troco.setVisible(false);
		trocoValor.setVisible(false);
		valorPagarField.setText("");
		this.venderProdutos.fireTableDataChanged();
	}

	//a parte de venda de serviço começa aqui
	private JPanel vendaServicoPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(venderOptionContentRetornPanel(), BorderLayout.NORTH);
		panel.add(venderServicoContentPanel());
		return panel;
	}

	private JPanel venderServicoContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(venderServicoContentBottomPanel());
		return panel;
	}


	private JPanel venderServicoContentBottomPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Tools.white);
		this.venderServicoItemSelecionadoTable = new JTable(this.venderProdutos);

		JLabel total = Tools.createLabel("Total:", null, Tools.paragraph);
		JLabel totalValue = Tools.createLabel("0", null, Tools.paragraph);
		JLabel qtStock = Tools.createLabel("0", null, Tools.paragraph);
		JLabel preco = Tools.createLabel("0kz", null, Tools.paragraph);
		JLabel tPagamento = Tools.createLabel("Tipo Pagamento", null, Tools.paragraph);
		JLabel valorPagar = Tools.createLabel("Valor entregue:", null, Tools.paragraph);
		JLabel troco = Tools.createLabel("Troco:", null, Tools.paragraph);
		JLabel trocoValor = Tools.createLabel("0kz", null, Tools.paragraph);
		JLabel valorCartao = Tools.createLabel("Valor entregue a cartão:", null, Tools.paragraph);
		JTextField valorPagarField = new JTextField();
		JTextField valorPagarCartaoField = new JTextField();
		Tools.alignRight(totalValue);
		Tools.alignRight(qtStock);
		Tools.alignRight(preco);
		Tools.alignRight(trocoValor);

		troco.setVisible(false);
		trocoValor.setVisible(false);

		//botao pra poder limpar todas vendas ou produtos adicionado a table
		JButton btnLimpar = Tools.createButton("Cancelar", Tools.redA, 100, 30, (e) ->{
			resetVenda(totalValue, troco, trocoValor, valorPagarField);
			valorPagar.setVisible(false);
			valorPagarField.setVisible(false);
			valorCartao.setVisible(false);
			valorPagarCartaoField.setVisible(false);
		});

		btnLimpar.setForeground(Tools.white);

		JButton btnVender = Tools.createButton("Vender", Tools.greenA, 100, 30, (e)->{
			if (venderServicoItemSelecionadoTable.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "Impossivel efectuar a venda porque não foi feito uma encomenda");
			}else {

				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				String date = df.format(new java.util.Date());

				int clienteId = ctrls.Cliente.SelectClienteId((String)this.venderServicoCliente.getSelectedItem());
				Object o[][] = ctrls.Encomenda.SelectTotalProdutoCategoriaQuantidade(clienteId);

				float totalV = Float.parseFloat(totalValue.getText().toString().endsWith("kz") ? 
						totalValue.getText().toString().substring(0, totalValue.getText().toString().length() - 2) : totalValue.getText().toString());

				Object[][] itens = new Object[venderServicoItemSelecionadoTable.getRowCount()][3];
				for (int i = 0; i < venderServicoItemSelecionadoTable.getRowCount(); i++) {
					for (int j = 0; j < 3; j++) {
						itens[i][j] =  venderServicoItemSelecionadoTable.getValueAt(i, j);
					}
				}
				
				/*Object[][] itens = new Object[itemSelecionadoTable.getRowCount()][3];
				for (int i = 0; i < itemSelecionadoTable.getRowCount(); i++) {
					for (int j = 0; j < 3; j++) {
						itens[i][j] =  itemSelecionadoTable.getValueAt(i, j);
					}
				}*/

				faturaMazeda.addItens(itens.length, itens);

				if (!((String)this.tipoPagamentoServico.getSelectedItem()).equals("Dinheiro")) {

					if (((String)this.tipoPagamentoServico.getSelectedItem()).equals("Cartão")) {
						//efectua a venda
						int tipoPagamentoId = ctrls.TipoPagamento.SelectTipoPagamentoId((String)this.tipoPagamentoServico.getSelectedItem());

						ctrls.VenderServico.venderServico(tipoPagamentoId, 0, totalV, clienteId, this.BIfuncionario);
						//ctrls.VenderServico.produtosEncomendados(produtoCategorias, produtoQuantidade);


						try {
							ctrls.VenderServico.produtosEncomendadosSelecionados(o);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						try {
							faturaMazeda.fatura((String)this.venderCliente.getSelectedItem(), (String)this.tipoPagamento.getSelectedItem(),
									Integer.parseInt(vendaNumber.getText()), totalV, totalV, 0);
							ctrls.PrintPdf.executeFatura();
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage());
						}

						pagamentos.addRow(ctrls.Pagamento.SelectLastPagamentoId(), (String)this.tipoPagamento.getSelectedItem(), 
								totalV, 0, totalV);

						vendasServico.addRow(ctrls.VendaServico.SelectLastVendaServicoId(), ctrls.Funcionario.selectFuncionarioNome(this.BIfuncionario), 
								totalV, date.replace('/', '-'));

						for (int i = 0; i < itens.length; i++) {
							if (encomendasEfectuadas.exists((String)itens[i][0], (int)itens[i][1])) {
								encomendasEfectuadas.appendValue((String)itens[i][0], (int)itens[i][1]);
							}else {
								encomendasEfectuadas.addRow((String)itens[i][0], (int)itens[i][1]);
							}

							if (encomendas.exists((String)itens[i][0], (String)this.venderServicoCliente.getSelectedItem(), 
									"em processo")) {
								encomendas.updateEncomendaEstado((String)itens[i][0], (String)this.venderServicoCliente.getSelectedItem(), 
										"em processo"); 
							}
						}


						pagamentos.fireTableDataChanged();
						vendasServico.fireTableDataChanged();
						encomendasEfectuadas.fireTableDataChanged();
						encomendas.fireTableDataChanged();

						this.venderServicoCliente.removeItem(this.venderServicoCliente.getSelectedItem());
						vendaNumber.setText("" + ((Integer.parseInt(vendaNumber.getText())) + 1));
						JOptionPane.showMessageDialog(this, "Venda efectuada com sucesso!");
						//resetVenda(totalValue, troco, trocoValor, valorPagarField);
					}else {

						//venda de servico a misto

						if (valorPagarField.getText().isEmpty() || valorPagarCartaoField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(this, "Por favor Insira o valor entregue pelo cliente ou o valor a cartão");
						}else {
							float valorMistoDinheiro = Float.parseFloat(valorPagarField.getText());
							float valorMistoCartao = Float.parseFloat(valorPagarCartaoField.getText());
							float valorTotalMisto = valorMistoDinheiro + valorMistoCartao; 

							if (valorTotalMisto < totalV) {
								JOptionPane.showMessageDialog(this, "O valor pago é insuficiente para efectuar a venda");
							}else{

								troco.setVisible(true);
								trocoValor.setVisible(true);

								int tipoPagamentoId = ctrls.TipoPagamento.SelectTipoPagamentoId((String)this.tipoPagamento.getSelectedItem());

								/*String produtoCategorias = venderProdutos.AllCategorias();
								String produtoQuantidade = venderProdutos.AllQuantidades();*/

								float trocoV = valorTotalMisto - totalV;

								valorMistoDinheiro = valorMistoDinheiro - trocoV;

								ctrls.VenderServico.venderServico(tipoPagamentoId, valorMistoDinheiro, valorMistoCartao, clienteId, this.BIfuncionario);
								try {
									ctrls.VenderServico.produtosEncomendadosSelecionados(o);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								
								try {
									faturaMazeda.fatura((String)this.venderCliente.getSelectedItem(), (String)this.tipoPagamento.getSelectedItem(),
											Integer.parseInt(vendaNumber.getText()), totalV, (valorTotalMisto),
											((valorTotalMisto) - totalV));
									ctrls.PrintPdf.executeFatura();
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, e2.getMessage());
								}

								pagamentos.addRow(ctrls.Pagamento.SelectLastPagamentoId(), (String)this.tipoPagamento.getSelectedItem(), 
										totalV, valorMistoDinheiro, valorMistoCartao);
								vendasServico.addRow(ctrls.VendaServico.SelectLastVendaServicoId(), ctrls.Funcionario.selectFuncionarioNome(this.BIfuncionario), 
										totalV, date.replace('/', '-'));

								for (int i = 0; i < itens.length; i++) {

									if (encomendasEfectuadas.exists((String)itens[i][0], (int)itens[i][1])) {
										encomendasEfectuadas.appendValue((String)itens[i][0], (int)itens[i][1]);
									}else {
										encomendasEfectuadas.addRow((String)itens[i][0], (int)itens[i][1]);
									}

									if (encomendas.exists((String)itens[i][0], (String)this.venderServicoCliente.getSelectedItem(), 
											"em processo")) {
										encomendas.updateEncomendaEstado((String)itens[i][0], (String)this.venderServicoCliente.getSelectedItem(), 
												"em processo"); 
									}
								}

								pagamentos.fireTableDataChanged();
								vendasServico.fireTableDataChanged();
								encomendasEfectuadas.fireTableDataChanged();
								encomendas.fireTableDataChanged();

								this.venderServicoCliente.removeItem(this.venderServicoCliente.getSelectedItem());
								vendaNumber.setText("" + ((Integer.parseInt(vendaNumber.getText())) + 1));
								JOptionPane.showMessageDialog(this, "Venda efectuada com sucesso!");

								trocoValor.setText("" + (trocoV) + "kz");
							}


						}

					}
				}else {

					//venda de serviço a dinheiro

					if (valorPagarField.getText().equals("")) {
						JOptionPane.showMessageDialog(this, "Por favor Insira o valor entregue pelo cliente");
					}else {

						float valorEntrege = Float.parseFloat(valorPagarField.getText());

						if (valorEntrege < totalV) {
							JOptionPane.showMessageDialog(this, "Valor entregue insufieciente para efectuar a venda");
						}else {
							troco.setVisible(true);
							trocoValor.setVisible(true);
							int tipoPagamentoId = ctrls.TipoPagamento.SelectTipoPagamentoId((String)this.tipoPagamento.getSelectedItem());

							ctrls.VenderServico.venderServico(tipoPagamentoId, totalV, 0, clienteId, this.BIfuncionario);

							try {
								ctrls.VenderServico.produtosEncomendadosSelecionados(o);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							try {
								faturaMazeda.fatura((String)this.venderCliente.getSelectedItem(), (String)this.tipoPagamento.getSelectedItem(),
										Integer.parseInt(vendaNumber.getText()), totalV, valorEntrege, (valorEntrege - totalV));
								ctrls.PrintPdf.executeFatura();
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, e2.getMessage());
							}

							pagamentos.addRow(ctrls.Pagamento.SelectLastPagamentoId(), (String)this.tipoPagamento.getSelectedItem(), 
									totalV, totalV, 0);
							vendasServico.addRow(ctrls.VendaServico.SelectLastVendaServicoId(), ctrls.Funcionario.selectFuncionarioNome(this.BIfuncionario), 
									totalV, date.replace('/', '-'));

							for (int i = 0; i < itens.length; i++) {

								if (encomendasEfectuadas.exists((String)itens[i][0], (int)itens[i][1])) {
									encomendasEfectuadas.appendValue((String)itens[i][0], (int)itens[i][1]);
								}else {
									encomendasEfectuadas.addRow((String)itens[i][0], (int)itens[i][1]);
								}

								if (encomendas.exists((String)itens[i][0], (String)this.venderServicoCliente.getSelectedItem(), 
										"em processo")) {
									encomendas.updateEncomendaEstado((String)itens[i][0], (String)this.venderServicoCliente.getSelectedItem(), 
											"em processo"); 
								}
							}

							pagamentos.fireTableDataChanged();
							vendasServico.fireTableDataChanged();
							encomendasEfectuadas.fireTableDataChanged();
							encomendas.fireTableDataChanged();

							this.venderServicoCliente.removeItem(this.venderServicoCliente.getSelectedItem());
							vendaNumber.setText("" + ((Integer.parseInt(vendaNumber.getText())) + 1));
							JOptionPane.showMessageDialog(this, "Venda efectuada com sucesso!");
						}

					}
				}
				/**/
				encomendaCount.setText("" + encomendas.count());
				vendasCount.setText("" + ((Integer.parseInt(vendasCount.getText()) + 1)));
			}
			
		});
		btnVender.setForeground(Tools.white);

		addComponent(panel, Tools.createLabel("Selecionar Cliente", null, Tools.paragraph), 10, 80, 300, 30);

		ArrayList<String> allClientes = new ArrayList<String>();
		ctrls.Cliente.SelectAllClientesEncomenda(allClientes);
		this.venderServicoCliente.addItem("");

		for (String cliente : allClientes) {
			this.venderServicoCliente.addItem(cliente);
		}



		this.venderServicoCliente.addItemListener((e) ->{
			if (!(this.venderServicoCliente.getSelectedItem().equals(""))) {

				resetVenda(totalValue, troco, trocoValor, valorPagarField);

				int clienteId = ctrls.Cliente.SelectClienteId((String)this.venderServicoCliente.getSelectedItem());
				Object o[][] = ctrls.Encomenda.SelectTotalProdutoCategoriaQuantidade(clienteId);
				float a = 0;

				for (int i = 0; i < o.length; i++) {
					int value = (int)o[i][1];
					int pay =  (int)o[i][2];
					a = (pay*value);

					if (this.venderServicoItemSelecionadoTable.getRowCount() < o.length) {
						this.venderProdutos.addRow(
								value, (String)o[i][0],  a);
					}

				}

				float sum = 0;
				for (int i = 0; i < o.length; i++) {
					sum += Float.parseFloat(venderServicoItemSelecionadoTable.getValueAt(i, 2).toString());
				}

				totalValue.setText("" + sum + "kz");

				this.venderProdutos.fireTableDataChanged();
			}
		});

		addComponent(panel, this.venderServicoCliente, 10, 110, 300, 30);

		addComponent(panel, btnLimpar, 10, 350, 100, 30);
		addComponent(panel, btnVender, ((this.getWidth()) - 130), 380, 100, 30);
		addComponent(panel, new JScrollPane(this.venderServicoItemSelecionadoTable), ((this.getWidth() / 2) + 20), 10, ((this.getWidth() / 2) - 50), (this.getHeight() / 2));
		addComponent(panel, tPagamento, 570, (this.getHeight() / 2) + 10, 130, 30);
		addComponent(panel, valorCartao, 570, 350, 200, 30);
		addComponent(panel, valorPagarCartaoField, 570, 380, 230, 30);

		ArrayList<String> allTipoPagamentos = new ArrayList<String>();
		ctrls.TipoPagamento.selectAllTipoPagamento(allTipoPagamentos);

		for (String tipoPagamento : allTipoPagamentos) {
			this.tipoPagamentoServico.addItem(tipoPagamento);
		}

		this.tipoPagamentoServico.addItemListener((e) ->{
			if (this.tipoPagamentoServico.getSelectedItem().equals("Cartão")) {
				valorPagar.setVisible(false);
				valorPagarField.setVisible(false);
				valorCartao.setVisible(false);
				valorPagarCartaoField.setVisible(false);
			}else if(this.tipoPagamentoServico.getSelectedItem().equals("Dinheiro")) {
				valorPagar.setVisible(true);
				valorPagarField.setVisible(true);
				valorCartao.setVisible(false);
				valorPagarCartaoField.setVisible(false);
			}else if(this.tipoPagamentoServico.getSelectedItem().equals("Misto")) {
				valorPagar.setVisible(true);
				valorPagarField.setVisible(true);
				valorCartao.setVisible(true);
				valorPagarCartaoField.setVisible(true);
			}
		});

		valorPagar.setVisible(false);
		valorPagarField.setVisible(false);
		valorCartao.setVisible(false);
		valorPagarCartaoField.setVisible(false);

		addComponent(panel, this.tipoPagamentoServico, 570, 320, 110, 30);
		addComponent(panel, valorPagar, 700, (this.getHeight() / 2) + 10, 130, 30);
		addComponent(panel, valorPagarField, 700, 320, 100, 30);
		addComponent(panel, troco, ((this.getWidth()) - 200), (this.getHeight() / 2) + 60, 170, 30);
		addComponent(panel, trocoValor, ((this.getWidth()) - 200), (this.getHeight() / 2) + 60, 170, 30);
		addComponent(panel, total, (this.getWidth()) - 200, (this.getHeight() / 2) + 10, 170, 30);
		addComponent(panel, totalValue, (this.getWidth()) - 200, (this.getHeight() / 2) + 10, 170, 30);

		/*this.venderProdutoTable.getSelectionModel().addListSelectionListener((e) -> {

			});*/

		return panel;
	}

	// a parte de venda de servico termina aqui (: </>

	private JPanel venderOptionContentRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::vender), BorderLayout.WEST);
		return panel;
	}

	/*a parte de vender termina aqui </> :)*/

	/*a parte da estatistica começa aqui  </> :)*/

	private JPanel estatisticaPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);

		panel.add(Tools.createLabel("Estatistica", null, Tools.header));

		JPanel StatPanels[][] = createPanelArray();	

		JLabel funcionarioTitle = Tools.createLabel("Funcionarios", null, Tools.header2);
		funcionarioCount.setText("" + ctrls.Funcionario.countFuncionario());
		funcionarioTitle.setHorizontalAlignment(JLabel.CENTER);
		funcionarioCount.setHorizontalAlignment(JLabel.CENTER);
		StatPanels[0][0].add(funcionarioTitle, BorderLayout.SOUTH);
		StatPanels[0][0].add(funcionarioCount);

		JLabel clienteTitle = Tools.createLabel("Clientes", null, Tools.header2);
		clientCount.setText("" + ctrls.Cliente.countCliente());
		clienteTitle.setHorizontalAlignment(JLabel.CENTER);
		clientCount.setHorizontalAlignment(JLabel.CENTER);
		StatPanels[0][1].add(clienteTitle, BorderLayout.SOUTH);
		StatPanels[0][1].add(clientCount);

		JLabel fornecedorTitle = Tools.createLabel("Fornecedores", null, Tools.header2);
		fornecedorCount.setText("" + ctrls.Fornecedor.countFornecedor());
		fornecedorTitle.setHorizontalAlignment(JLabel.CENTER);
		fornecedorCount.setHorizontalAlignment(JLabel.CENTER);
		StatPanels[0][2].add(fornecedorTitle, BorderLayout.SOUTH);
		StatPanels[0][2].add(fornecedorCount);
		
		JLabel vendasTitle = Tools.createLabel("Vendas Efectuadas", null, Tools.header2);
		vendasCount.setText("" + ctrls.Venda.selectVendasTotal());
		vendasTitle.setHorizontalAlignment(JLabel.CENTER);
		vendasCount.setHorizontalAlignment(JLabel.CENTER);
		StatPanels[0][3].add(vendasTitle, BorderLayout.SOUTH);
		StatPanels[0][3].add(vendasCount);

		JLabel encomedaTitle = Tools.createLabel("Encomendas", null, Tools.header2);
		encomendaCount.setText("" + ctrls.Encomenda.countEncomendaEmProcesso());
		encomedaTitle.setHorizontalAlignment(JLabel.CENTER);
		encomendaCount.setHorizontalAlignment(JLabel.CENTER);
		StatPanels[1][0].add(encomedaTitle, BorderLayout.SOUTH);
		StatPanels[1][0].add(encomendaCount);

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				StatPanels[i][j].setBackground(Tools.statisticColors[(int)(0 + Math.random() * (Tools.statisticColors.length-0))]);
				StatPanels[i][j].setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				panel.add(StatPanels[i][j]);
			}
		}
		return panel;
	}


	private JPanel[][] createPanelArray() {
		JPanel panel[][] = new JPanel[2][4];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				panel[i][j] = new JPanel(new BorderLayout());
			}
		}
		return panel;
	}

	/*a parte da estatistica termina aqui </> :)*/

	/*a parte do usuario do sistema começa aqui  </> :)*/

	private JPanel usersOptionPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(optionRetornPanel(), BorderLayout.NORTH);
		panel.add(userPanel());
		return panel;
	}

	private JPanel userPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Usuarios", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createButton(
				"adicionar usuario", Tools.createIcon("/img/icons8_Add_User_Male_96px.png", 40), Tools.lightgrey, 100, 100, this::addUsuarios));
		panel.add(Tools.createButton(
				"", Tools.createIcon("", 40), Tools.white, 100, 100, null));
		panel.add(Tools.createButton(
				"Ver usuarios", Tools.createIcon("/img/icons8_View_96px.png", 40), Tools.lightgrey, 100, 100, this::viewUsers));

		return panel;
	}

	private JPanel userAddPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(userViewContentRetornPanel(), BorderLayout.NORTH);
		panel.add(new JScrollPane(userAddContentPanel()));
		return panel;
	}

	private JPanel userAddContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Tools.white);
		JLabel funcionario = Tools.createLabel("Funcionario:", null, Tools.paragraph);
		addComponent(panel, funcionario, 350, 100, 100, 30);

		ArrayList<String> biList = new ArrayList<String>();
		ctrls.Funcionario.SelectAllFuncionario(biList);
		for (String bi: biList) {
			userFuncionario.addItem(bi);
		}

		addComponent(panel, userFuncionario, 350, 130, 400, 30);
		JLabel username = Tools.createLabel("Nome do usuario:", null, Tools.paragraph);
		addComponent(panel, username, 350, 180, 150, 30);
		addComponent(panel, userUsername, 350, 210, 150, 30);
		JLabel password = Tools.createLabel("Senha:", null, Tools.paragraph);
		addComponent(panel, password, 600, 180, 150, 30);
		addComponent(panel, userPassword, 600, 210, 150, 30);

		cadastrarUser = Tools.createButton("Cadastrar usuario", Tools.greenA, 200, 40, this::addUser);
		atualizarUser = Tools.createButton("atualizar usuario", Tools.blueA, 200, 40, this::updateUser);
		cadastrarUser.setForeground(Tools.white);
		atualizarUser.setForeground(Tools.white);

		addComponent(panel, cadastrarUser, 600, 350, 150, 40);
		addComponent(panel, atualizarUser, 600, 350, 150, 40);


		return panel;
	}


	//parte de visualizar a categoria do produto mesmo codico com a de visualizar os produtos
	private JPanel userViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(userViewContentRetornPanel(), BorderLayout.NORTH);
		panel.add(userViewContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todas as categorias dos produtos
	private JPanel userViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.userSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(usersTable()));
		panel.add(userUpdatePanel(), BorderLayout.SOUTH);
		return panel;
	}


	private JTable usersTable() {
		usersSorter = new TableRowSorter<UsersAll>(users);
		this.UsersTable = new JTable(users);
		this.UsersTable.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.UsersTable.setRowSorter(usersSorter);
		return this.UsersTable;
	}


	//barra de panel onde vai conter os botoes de atualizar dados das categorias do produtos
	private JPanel userUpdatePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel.setBackground(Tools.white);
		panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
				Tools.lightgrey, 40, 40, this::updateUserSetDados));
		panel.add(Tools.createButton("Apagar", Tools.createIcon("/img/icons8_Cancel_96px.png", 40), 
				Tools.lightgrey, 40, 40, this::eliminarUser));
		return panel;
	}
	/**/


	private JPanel userViewContentRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showUsersPanel), BorderLayout.WEST);
		return panel;
	}

	/*a parte  do usuario do sistema  termina aqui </> :)*/



	/*a parte da venda que esta em mais opcoes começa aqui  </> :)*/

	private JPanel vendaOptionPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(optionRetornPanel(), BorderLayout.NORTH);
		panel.add(vendaPanel());
		return panel;
	}

	private JPanel vendaPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Vendas", null, Tools.header);
		panel.add(header);
		panel.add(new JLabel());
		panel.add(Tools.createButton(
				"Ver Vendas feitas", Tools.createIcon("/img/icons8_View_96px.png", 40), Tools.lightgrey, 100, 100, this::viewVendas));
		panel.add(Tools.createButton(
				"Ver encomendas processadas", Tools.createIcon("/img/icons8_View_96px.png", 40), Tools.lightgrey, 100, 100, this::viewVendaServico));
		return panel;
	}


	//parte de visualizar a categoria do produto mesmo codico com a de visualizar os produtos
	private JPanel vendaViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(vendaViewContentRetornPanel(), BorderLayout.NORTH);
		panel.add(vendaViewContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todas as categorias dos produtos
	private JPanel vendaViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.vendaSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(vendasTable()));
		//panel.add(vendaUpdatePanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable vendasTable() {
		this.vendaSorter = new TableRowSorter<VendaAll>(vendas);
		this.vendasEfectuadas = new JTable(vendas);
		this.vendasEfectuadas.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.vendasEfectuadas.setRowSorter(this.vendaSorter);
		return this.vendasEfectuadas;
	}

	//parte de visualizar a categoria do produto mesmo codico com a de visualizar os produtos
	private JPanel vendaServicoViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(vendaViewContentRetornPanel(), BorderLayout.NORTH);
		panel.add(vendaServicoViewContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todas as categorias dos produtos
	private JPanel vendaServicoViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.vendaServicoSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(vendaServicosTable()));
		return panel;
	}

	private JTable vendaServicosTable() {
		this.vendaServicoSorter = new TableRowSorter<dal.VendaServicoAll>(vendasServico);
		this.VendasServicoTable = new JTable(vendasServico);
		this.VendasServicoTable.setRowHeight(30);
		this.VendasServicoTable.setRowSorter(this.vendaServicoSorter);
		return this.VendasServicoTable;
	}

	//barra de panel onde vai conter os botoes de atualizar dados das categorias do produtos
	/*private JPanel vendaUpdatePanel() {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panel.setBackground(Tools.white);
			panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("../img/icons8_Restart_96px_1.png", 40), 
					Tools.lightgrey, 40, 40, null));
			panel.add(Tools.createButton("Apagar", Tools.createIcon("../img/icons8_Cancel_96px.png", 40), 
					Tools.lightgrey, 40, 40, null));
			return panel;
		}*/
	/**/


	private JPanel vendaViewContentRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showVendasPanel), BorderLayout.WEST);
		return panel;
	}

	/*a parte  da venda  termina aqui </> :)*/


	/*a parte do pagamento que esta em mais opcoes começa aqui  </> :)*/

	private JPanel pagamentoOptionPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(optionRetornPanel(), BorderLayout.NORTH);
		panel.add(pagamentoPanel());
		return panel;
	}

	private JPanel pagamentoPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Pagametos", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createButton(
				"Ver todos os pagamentos", Tools.createIcon("/img/icons8_View_96px.png", 40), Tools.lightgrey, 100, 100, this::viewPagamentos));

		return panel;
	}


	//parte de visualizar a categoria do produto mesmo codico com a de visualizar os produtos
	private JPanel pagamentoViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(pagamentoViewContentRetornPanel(), BorderLayout.NORTH);
		panel.add(pagamentoViewContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todas as categorias dos produtos
	private JPanel pagamentoViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.pagamentoSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(pagamentosTable()));
		//panel.add(pagamentoUpdatePanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable pagamentosTable() {
		pagamentosSorter = new TableRowSorter<PagamentosAll>(pagamentos);
		this.PagamentosTable = new JTable(pagamentos);
		this.PagamentosTable.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.PagamentosTable.setRowSorter(pagamentosSorter);
		return this.PagamentosTable;
	}

	//barra de panel onde vai conter os botoes de atualizar dados das categorias do produtos
	private JPanel pagamentoUpdatePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel.setBackground(Tools.white);
		panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
				Tools.lightgrey, 40, 40, null));
		panel.add(Tools.createButton("Apagar", Tools.createIcon("/img/icons8_Cancel_96px.png", 40), 
				Tools.lightgrey, 40, 40, null));
		return panel;
	}
	/**/


	private JPanel pagamentoViewContentRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showPagamentosPanel), BorderLayout.WEST);
		return panel;
	}

	/*a parte  do pagamento  termina aqui </> :)*/


	/*a parte do equipamentos que esta em mais opcoes começa aqui  </> :)*/

	private JPanel equipamentoOptionPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(optionRetornPanel(), BorderLayout.NORTH);
		panel.add(equipamentoPanel());
		return panel;
	}

	private JPanel equipamentoPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Equipamentos", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createButton(
				"", Tools.createIcon("", 40), Tools.white, 100, 100, null));
		panel.add(Tools.createButton(
				"adicionar equipamentos", Tools.createIcon("/img/icons8_Plus_96px.png", 40), Tools.lightgrey, 100, 100, this::addEquipamentos));
		panel.add(Tools.createButton(
				"Ver todos os equipamentos", Tools.createIcon("/img/icons8_View_96px.png", 40), Tools.lightgrey, 100, 100, this::viewEquipamentosInfo));

		return panel;
	}

	/*private JPanel equipamentoAddMoreOptionsPanel() {
			JPanel panel = new JPanel(new BorderLayout());
			panel.add(equipamentoViewContentRetornPanel(), BorderLayout.NORTH);
			panel.add(equipamentoAddMoreOptionsContent());
			return panel;
		}

		private JPanel equipamentoAddMoreOptionsContent() {
			JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
			panel.setBorder(new EmptyBorder(100, 150, 100, 150));
			panel.setBackground(Tools.white);
			JLabel header = Tools.createLabel("Equipamentos +", null, Tools.header);
			panel.add(header);
			panel.add(Tools.createButton(
					"", Tools.createIcon("", 40), Tools.white, 100, 100, null));
			panel.add(Tools.createButton(
					"adicionar equipamentos", Tools.createIcon("../img/icons8_Plus_96px.png", 40), Tools.lightgrey, 100, 100, this::addEquipamentos));
			/*panel.add(Tools.createButton(
					"adicionar estado de equipamentos", Tools.createIcon("../img/icons8_Plus_96px.png", 40), Tools.lightgrey, 100, 100, this::addEquipamentoEstado));
	 */
	/*return panel;
		}*/

	private JPanel equipamentoAddPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(equipamentoAddContentRetornPanel(), BorderLayout.NORTH);
		panel.add(new JScrollPane(equipamentoAddContentPanel()));
		return panel;
	}

	private JPanel equipamentoAddContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Tools.white);
		JLabel nome = Tools.createLabel("Nome:", null, Tools.paragraph);
		addComponent(panel, nome, 350, 150, 150, 30);
		addComponent(panel, equipamentoNome, 350, 180, 150, 30);
		JLabel estado = Tools.createLabel("Estado do equipamento:", null, Tools.paragraph);
		addComponent(panel, estado, 600, 150, 200, 30);

		ArrayList<String> estadoList = new ArrayList<String>();
		ctrls.EstadoEquipamento.SelectAllEstado(estadoList);
		for (String estados: estadoList) {
			equipamentoEstado.addItem(estados);
		}

		addComponent(panel, equipamentoEstado, 600, 180, 150, 30);

		cadastrarEquipamento = Tools.createButton("Cadastrar Equipamento", Tools.greenA, 200, 40, this::addEquipamento);
		atualizarEquipamento = Tools.createButton("Atualizar Equipamento", Tools.blueA, 200, 40, this::updateEquipamento);
		cadastrarEquipamento.setForeground(Tools.white);
		atualizarEquipamento.setForeground(Tools.white);

		addComponent(panel, cadastrarEquipamento, 600, 350, 150, 40);
		addComponent(panel, atualizarEquipamento, 600, 350, 150, 40);

		return panel;
	}

	private JPanel equipamentoEstadoAddPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(equipamentoAddContentRetornPanel(), BorderLayout.NORTH);
		panel.add(new JScrollPane(equipamentoEstadoAddContentPanel()));
		return panel;
	}

	private JPanel equipamentoEstadoAddContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Tools.white);
		JLabel nome = Tools.createLabel("Nome:", null, Tools.paragraph);
		addComponent(panel, nome, 420, 100, 100, 30);
		addComponent(panel, equipamentoEstadoNome, 420, 130, 200, 30);

		addComponent(panel, cadastrarEquipamentoEstado = Tools.createButton("Cadastrar estado de equipamento", 
				Tools.createIcon("/img/icons8_Plus_96px.png", 30), Tools.lightgrey, 200, 40, this::addEquipamentoEstadoInsert), 730, 350, 200, 40);
		addComponent(panel, atualizarEquipamentoEstado = Tools.createButton("atualizar estado de equipamento", 
				Tools.createIcon("/img/icons8_Restart_96px_1.png", 30), Tools.lightgrey, 200, 40, this::updateEquipamentoEstado), 730, 350, 200, 40);

		return panel;
	}

	//parte de visualizar a categoria do produto mesmo codico com a de visualizar os produtos
	private JPanel equipamentoViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(equipamentoViewContentRetornPanel(), BorderLayout.NORTH);
		panel.add(equipamentoViewMorePanel());
		return panel;
	}

	private JPanel equipamentoViewMorePanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Ver Equipamentos info", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createButton(
				"", Tools.createIcon("", 40), Tools.white, 100, 100, null));
		panel.add(Tools.createButton(
				"ver equipamentos", Tools.createIcon("/img/icons8_View_96px.png", 40), Tools.lightgrey, 100, 100, this::viewEquipamentosInfo));
		/*panel.add(Tools.createButton(
					"ver estado de equipamentos", Tools.createIcon("/img/icons8_View_96px.png", 40), Tools.lightgrey, 100, 100, this::viewEquipamentosEstadoInfo));
		 */
		return panel;
	}

	private JPanel equipamentoViewMoreContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(equipamentoViewMoreContentRetornPanel(), BorderLayout.NORTH);
		panel.add(equipamentoViewContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todas as categorias dos produtos
	private JPanel equipamentoViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.equipamentoSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(equipamentosTable()));
		panel.add(equipamentoUpdatePanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable equipamentosTable() {
		equipamentosSorter = new TableRowSorter<EquipamentosAll>(equipamentos);
		this.EquipamentosTable = new JTable(equipamentos);
		this.EquipamentosTable.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.EquipamentosTable.setRowSorter(equipamentosSorter);
		return this.EquipamentosTable;
	}


	//barra de panel onde vai conter os botoes de atualizar dados das categorias do produtos
	private JPanel equipamentoUpdatePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel.setBackground(Tools.white);
		panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
				Tools.lightgrey, 40, 40, this::updateEquipamentoSetDados));
		panel.add(Tools.createButton("Apagar", Tools.createIcon("/img/icons8_Cancel_96px.png", 40), 
				Tools.lightgrey, 40, 40, this::eliminarEquipamento));
		return panel;
	}


	/**/

	private JPanel equipamentoViewEstadoMoreContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(equipamentoViewMoreContentRetornPanel(), BorderLayout.NORTH);
		panel.add(equipamentoViewEstadoContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todas as categorias dos produtos
	private JPanel equipamentoViewEstadoContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.equipamentoEstadoSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(equipamentosEstadoTable()));
		panel.add(equipamentoEstadoUpdatePanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable equipamentosEstadoTable() {
		equipamentosEstadoSorter = new TableRowSorter<EquipamentosEstadoAll>(estadosEquipamento);
		this.EquipamentosEstadoTable = new JTable(estadosEquipamento);
		this.EquipamentosEstadoTable.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.EquipamentosEstadoTable.setRowSorter(equipamentosEstadoSorter);
		return this.EquipamentosEstadoTable;
	}

	//barra de panel onde vai conter os botoes de atualizar dados das categorias do produtos
	private JPanel equipamentoEstadoUpdatePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel.setBackground(Tools.white);
		panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
				Tools.lightgrey, 40, 40, this::updateEquipamentoEstadoSetDados));
		panel.add(Tools.createButton("Apagar", Tools.createIcon("/img/icons8_Cancel_96px.png", 40), 
				Tools.lightgrey, 40, 40, this::eliminarEquipamentoEstado));
		return panel;
	}

	/**/


	private JPanel equipamentoViewContentRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showEquipamentosPanel), BorderLayout.WEST);
		return panel;
	}

	private JPanel equipamentoAddContentRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showEquipamentosPanel), BorderLayout.WEST);
		return panel;
	}

	private JPanel equipamentoViewMoreContentRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showEquipamentosPanel), BorderLayout.WEST);
		return panel;
	}

	/*a parte  do equipamentos  termina aqui </> :)*/


	/*a parte do servicos que esta em mais opcoes começa aqui  </> :)*/

	private JPanel servicoOptionPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(optionRetornPanel(), BorderLayout.NORTH);
		panel.add(servicoPanel());
		return panel;
	}

	private JPanel servicoPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Serviços", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createButton(
				"", Tools.createIcon("", 40), Tools.white, 100, 100, null));
		panel.add(Tools.createButton(
				"adicionar novos serviços", Tools.createIcon("/img/icons8_Plus_96px.png", 40), Tools.lightgrey, 100, 100, this::addServico));
		panel.add(Tools.createButton(
				"Ver todos os serviços desponiveis", Tools.createIcon("/img/icons8_View_96px.png", 40), Tools.lightgrey, 100, 100, this::viewServicos));

		return panel;
	}

	private JPanel servicoAddPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(servicoViewContentRetornPanel(), BorderLayout.NORTH);
		panel.add(new JScrollPane(servicoAddContentPanel()));
		return panel;
	}

	private JPanel servicoAddContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Tools.white);
		JLabel nome = Tools.createLabel("Nome:", null, Tools.paragraph);
		addComponent(panel, nome, 420, 100, 100, 30);
		addComponent(panel, servicoNome, 420, 130, 200, 30);

		cadastrarServico = Tools.createButton("adicionar novo serviço", Tools.greenA, 200, 40, this::addServicoInsert);
		atualizarServico = Tools.createButton("atualizar serviço", Tools.blueA, 200, 40, this::updateServico);
		cadastrarServico.setForeground(Tools.white);
		atualizarServico.setForeground(Tools.white);

		addComponent(panel, cadastrarServico, 730, 350, 200, 40);
		addComponent(panel, atualizarServico, 730, 350, 200, 40);

		return panel;
	}


	//parte de visualizar a categoria do produto mesmo codico com a de visualizar os produtos
	private JPanel servicoViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(servicoViewContentRetornPanel(), BorderLayout.NORTH);
		panel.add(servicoViewContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todas as categorias dos produtos
	private JPanel servicoViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.servicoSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(servicosTable()));
		panel.add(servicoUpdatePanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable servicosTable() {
		servicosSorter = new TableRowSorter<ServicosAll>(servicos);
		this.ServicosTable = new JTable(servicos);
		this.ServicosTable.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.ServicosTable.setRowSorter(servicosSorter);
		return this.ServicosTable;
	}

	//barra de panel onde vai conter os botoes de atualizar dados das categorias do produtos
	private JPanel servicoUpdatePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel.setBackground(Tools.white);
		panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
				Tools.lightgrey, 40, 40, this::updateServicoSetDados));
		panel.add(Tools.createButton("Apagar", Tools.createIcon("/img/icons8_Cancel_96px.png", 40), 
				Tools.lightgrey, 40, 40, this::eliminarServico));
		return panel;
	}
	/**/


	private JPanel servicoViewContentRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showServicosPanel), BorderLayout.WEST);
		return panel;
	}

	/*a parte  do serviços  termina aqui </> :)*/

	/*a parte da encomenda começa aqui </> :)**/

	private JPanel encomendaOptionPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(encomendaMoreOptionsReverse(), BorderLayout.NORTH);
		panel.add(encomendaPanel());
		return panel;
	}


	private JPanel encomendaPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Encomendas", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createLabel("", Tools.createIcon("", 50), null));
		panel.add(Tools.createButton(
				"Adicionar Encomendas Info", Tools.createIcon("/img/icons8_Plus_96px.png", 50), Tools.lightgrey, 100, 100, this::addEncomendas));
		panel.add(Tools.createButton(
				"Ver Encomendas info", Tools.createIcon("/img/icons8_View_96px.png", 50), Tools.lightgrey, 100, 100, this::viewEncomendas));

		return panel;
	}

	/*private JPanel encomendaAddOptionPanel() {
			JPanel panel = new JPanel(new BorderLayout());
			panel.add(encomendaRetornPanel(), BorderLayout.NORTH);
			panel.add(encomendaAddOptionShowPanel());
			return panel;
		}*/


	//metodo que mostra todas opcoes de inserção de produtos
	/*private JPanel encomendaAddOptionShowPanel() {
			JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
			panel.setBorder(new EmptyBorder(100, 150, 100, 150));
			panel.setBackground(Tools.white);
			JLabel header = Tools.createLabel("Adicionar Encomendas", null, Tools.header);
			panel.add(header);
			panel.add(Tools.createLabel("", null, null));
			/*panel.add(Tools.createButton(
					"Adicionar estado da encomenda", Tools.createIcon("/img/icons8_Plus_96px.png", 50), Tools.lightgrey, 100, 100, this::addEncomendaEstado));
	 */
	/*panel.add(Tools.createButton(
					"adicionar encomendas", Tools.createIcon("/img/icons8_Plus_96px.png", 50), Tools.lightgrey, 100, 100, this::addEncomendas));
			return panel;
		}*/

	/*private JPanel encomendaEstadoAddPanel() {
			JPanel panel = new JPanel(new BorderLayout());
			panel.add(encomendaAddContentRetornPanel(), BorderLayout.NORTH);
			panel.add(new JScrollPane(encomendaEstadoAddContentPanel()));
			return panel;
		}

		private JPanel encomendaEstadoAddContentPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBackground(Tools.white);
			JLabel nome = Tools.createLabel("Nome:", null, Tools.paragraph);
			addComponent(panel, nome, 420, 100, 100, 30);
			addComponent(panel, encomendaEstadoNome, 420, 130, 200, 30);


			addComponent(panel, cadastrarEncomendaEstado = Tools.createButton("Cadastrar estado da encomenda", 
					Tools.createIcon("/img/icons8_Plus_96px.png", 30), Tools.lightgrey, 200, 40, this::addEstadoEncomenda), 730, 350, 200, 40);
			addComponent(panel, atualizarEncomendaEstado = Tools.createButton("atualizar estado da encomenda", 
					Tools.createIcon("/img/icons8_Restart_96px_1.png", 30), Tools.lightgrey, 200, 40, this::updateEstadoEncomenda), 730, 350, 200, 40);

			return panel;
		}*/


	private JPanel encomendaAddPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(encomendaRetornPanel(), BorderLayout.NORTH);
		panel.add(new JScrollPane(encomendaAddContentPanel()));
		return panel;
	}

	private JPanel encomendaAddContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Tools.white);
		JLabel servico = Tools.createLabel("Serviço:", null, Tools.paragraph);
		addComponent(panel, servico, 250, 70, 100, 30);

		ArrayList<String> servicos = new ArrayList<String>();
		ctrls.Servico.SelectAllServico(servicos);

		for (String servicoAll : servicos) {
			this.encomendaServico.addItem(servicoAll);
		}

		addComponent(panel, encomendaServico, 250, 100, 200, 30);
		JLabel estado = Tools.createLabel("Estado:", null, Tools.paragraph);
		addComponent(panel, estado, 600, 70, 100, 30);

		ArrayList<String> estados = new ArrayList<String>();
		ctrls.EncomendaEstado.SelectAllEstadoEncomenda(estados);

		for (String estadoAll : estados) {
			this.encomendaEstado.addItem(estadoAll);
		}

		addComponent(panel, encomendaEstado, 600, 100, 200, 30);

		JLabel quantidade = Tools.createLabel("Quantidade:", null, Tools.paragraph);
		addComponent(panel, quantidade, 250, 140, 100, 30);

		addComponent(panel, encomendaQuantidade, 250, 170, 200, 30);

		JLabel produto = Tools.createLabel("Produto:", null, Tools.paragraph);
		addComponent(panel, produto, 600, 140, 100, 30);

		ArrayList<String> produtos = new ArrayList<String>();
		ctrls.Produto.SelectAllCategoriaExistingOnProduto(produtos);

		for (String produtosAll : produtos) {
			this.encomendaProduto.addItem(produtosAll);
		}


		addComponent(panel, encomendaProduto, 600, 170, 200, 30);

		JLabel cliente = Tools.createLabel("Cliente:", null, Tools.paragraph);
		addComponent(panel, cliente, 250, 200, 100, 30);

		ArrayList<String> clientes = new ArrayList<String>();
		ctrls.Cliente.SelectAllClientes(clientes);

		for (String clienteAll : clientes) {
			this.encomendaCliente.addItem(clienteAll);
		}

		addComponent(panel, encomendaCliente, 250, 230, 200, 30);
		JLabel data = Tools.createLabel("Data:", null, Tools.paragraph);
		addComponent(panel, data, 600, 200, 100, 30);
		addComponent(panel, encomendaData, 600, 230, 200, 30);
		cadastrarEncomenda = Tools.createButton("Cadastrar encomenda", Tools.greenA, 200, 40, this::addEncomenda);
		atualizarEncomenda = Tools.createButton("atualizar encomenda", Tools.lightgrey, 200, 40, null);
		cadastrarEncomenda.setForeground(Tools.white);
		atualizarEncomenda.setForeground(Tools.blueA);
		addComponent(panel, cadastrarEncomenda, 600, 350, 200, 40);
		addComponent(panel, atualizarEncomenda, 600, 350, 200, 40);

		return panel;
	}


	/*private JPanel encomendaViewOptionPanel() {
			JPanel panel = new JPanel(new BorderLayout());
			panel.add(encomendaRetornPanel(), BorderLayout.NORTH);
			panel.add(encomendaViewOptionShowPanel());
			return panel;
		}


		//metodo que mostra todas opcoes de inserção de produtos
		private JPanel encomendaViewOptionShowPanel() {
			JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
			panel.setBorder(new EmptyBorder(100, 150, 100, 150));
			panel.setBackground(Tools.white);
			JLabel header = Tools.createLabel("ver encomendas", null, Tools.header);
			panel.add(header);
			panel.add(Tools.createLabel("", null, null));
			/*panel.add(Tools.createButton(
					"Visualizar Estado das encomendas", Tools.createIcon("/img/icons8_View_96px.png", 50), Tools.lightgrey, 100, 100, this::viewEncomendaEstado));
	 */
	/*panel.add(Tools.createButton(
					"Visualizar Encomendas", Tools.createIcon("/img/icons8_View_96px.png", 50), Tools.lightgrey, 100, 100, this::viewEncomendas));
			return panel;
		}*/

	private JPanel encomendaViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(encomendaRetornPanel(), BorderLayout.NORTH);
		panel.add(encomendaViewContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todos produtos
	private JPanel encomendaViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.encomendaSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(encomendasTable()));
		panel.add(encomendaUpdatePanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable encomendasTable() {
		encomendaSorter = new TableRowSorter<EncomendaAll>(encomendas);
		this.encomendasTable = new JTable(encomendas);
		this.encomendasTable.setRowHeight(30);
		this.encomendasTable.setRowSorter(encomendaSorter);
		return this.encomendasTable;
	}

	//barra de panel onde vai conter os botoes de atualizar dados dos produtos
	private JPanel encomendaUpdatePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel.setBackground(Tools.white);
		panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
				Tools.lightgrey, 40, 40, null));
		panel.add(Tools.createButton("Apagar", Tools.createIcon("/img/icons8_Cancel_96px.png", 40), 
				Tools.lightgrey, 40, 40, this::eliminarEncomenda));
		return panel;
	}

	private JPanel encomendaEstadoViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(encomendaViewContentRetornPanel(), BorderLayout.NORTH);
		panel.add(encomendaEstadoViewContentPanel());
		return panel;
	}


	//aqui vai estar a tabela que vai mostrar todos produtos
	private JPanel encomendaEstadoViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.encomendaEstadoSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(encomendaEstadoTable()));
		panel.add(encomendaEstadoUpdatePanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable encomendaEstadoTable() {
		this.encomendaEstadoSorter = new TableRowSorter<EncomendasEstadoAll>(estadosEncomenda);
		this.EncomendasEstadoTable = new JTable(estadosEncomenda);
		this.EncomendasEstadoTable.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.EncomendasEstadoTable.setRowSorter(this.encomendaEstadoSorter);
		return this.EncomendasEstadoTable;
	}


	//barra de panel onde vai conter os botoes de atualizar dados dos produtos
	private JPanel encomendaEstadoUpdatePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel.setBackground(Tools.white);
		panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
				Tools.lightgrey, 40, 40, this::updateEstadoEncomendaSetDados));
		panel.add(Tools.createButton("Apagar", Tools.createIcon("/img/icons8_Cancel_96px.png", 40), 
				Tools.lightgrey, 40, 40, this::eliminarEstadoEncomenda));
		return panel;
	}


	private JPanel encomendaAddContentRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::encomendaAddOptionPanel), BorderLayout.WEST);
		return panel;
	}

	private JPanel encomendaViewContentRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::encomendaViewOptionPanel), BorderLayout.WEST);
		return panel;
	}

	private JPanel encomendaRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showEncomendasPanel), BorderLayout.WEST);
		return panel;
	}

	private JButton encomendaMoreOptionsReverseBtn() {
		return this.EncomendaMoreOptionsReverse = reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showMoreOptions);
	}

	private JPanel encomendaMoreOptionsReverse() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(encomendaMoreOptionsReverseBtn(), BorderLayout.WEST);
		return panel;
	}

	/*a parte  da encomenda  termina aqui </> :)*/

	/*a parte  de stock  começa aqui </> :)*/

	private JPanel stockOptionPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(optionRetornPanel(), BorderLayout.NORTH);
		panel.add(stockPanel());
		return panel;
	}

	private JPanel stockPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
		panel.setBorder(new EmptyBorder(100, 150, 100, 150));
		panel.setBackground(Tools.white);
		JLabel header = Tools.createLabel("Visualizar stock", null, Tools.header);
		panel.add(header);
		panel.add(Tools.createLabel("", null, null));
		panel.add(Tools.createButton(
				"adicionar produto de entrada", Tools.createIcon("/img/icons8_Plus_96px.png", 40), Tools.lightgrey, 100, 100, this::addProdutoEntrada));
		panel.add(Tools.createButton(
				"ver produtos que entraram", Tools.createIcon("/img/icons8_Front_Gate_Open_48px_2.png", 40), Tools.lightgrey, 100, 100, this::viewStockProdutoEntrada));
		panel.add(Tools.createButton(
				"ver produtos vendidos", Tools.createIcon("/img/icons8_In_Transit_48px.png", 40), Tools.lightgrey, 100, 100, this::viewStockProdutoSaida));
		panel.add(Tools.createButton(
				"ver encomendas efectuadas", Tools.createIcon("/img/icons8_In_Transit_48px.png", 40), Tools.lightgrey, 100, 100, this::viewStockProdutoEncomendaEfectuada));
		return panel;
	}

	private JPanel produtoEntradaAddPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(stockRetornPanel(), BorderLayout.NORTH);
		panel.add(produtoEntradaAddContentPanel());
		return panel;
	}

	private JPanel produtoEntradaAddContentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Tools.white);
		JLabel data = Tools.createLabel("Data:", null, Tools.paragraph);
		addComponent(panel, data, 250, 100, 100, 30);
		addComponent(panel, produtoEntradaData, 250, 130, 200, 30);
		JLabel quantidade = Tools.createLabel("Quantidade:", null, Tools.paragraph);
		addComponent(panel, quantidade, 600, 100, 100, 30);
		addComponent(panel, produtoEntradaQuantidade, 600, 130, 200, 30);
		JLabel fornecedor = Tools.createLabel("fornecedor:", null, Tools.paragraph);
		addComponent(panel, fornecedor, 250, 180, 100, 30);

		ArrayList<String> fornecedorList = new ArrayList<String>();
		ctrls.Fornecedor.SelectAllFornecedor(fornecedorList);
		for (String fornecedores: fornecedorList) {
			produtoEntradaFornecedor.addItem(fornecedores);
		}

		addComponent(panel, produtoEntradaFornecedor, 250, 210, 200, 30);
		JLabel produto = Tools.createLabel("Produto:", null, Tools.paragraph);
		addComponent(panel, produto, 600, 180, 100, 30);

		ArrayList<String> produtoList = new ArrayList<String>();
		ctrls.Produto.SelectAllCategoriaExistingOnProduto(produtoList);
		for (String produtos: produtoList) {
			produtoEntradaProduto.addItem(produtos);
		}

		addComponent(panel, produtoEntradaProduto, 600, 210, 200, 30);

		cadastrarProdutoEntrada = Tools.createButton("adicionar Produto Entrada", Tools.greenA, 200, 40, this::addProdutoEntradaInsert);
		atualizarProdutoEntrada = Tools.createButton("Atualizar stock de entrada", Tools.blueA, 200, 40, null);
		cadastrarProdutoEntrada.setForeground(Tools.white);
		atualizarProdutoEntrada.setForeground(Tools.white);

		addComponent(panel, cadastrarProdutoEntrada , 600, 350, 200, 40);
		addComponent(panel, atualizarProdutoEntrada, 600, 350, 200, 40);

		return panel;
	}

	private JPanel stockProdutoEntradaViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(stockContentRetornPanel(), BorderLayout.NORTH);
		panel.add(stockProdutoEntradaViewContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todos produtos
	private JPanel stockProdutoEntradaViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.stockProdutoEntradaSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(produtoEntradaTable()));
		panel.add(stockProdutoEntradaUpdatePanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable produtoEntradaTable() {
		produtoEntradaSorter = new TableRowSorter<ProdutoEntradaAll>(produtosEntrada);
		this.produtosEntradaTable = new JTable(produtosEntrada);
		this.produtosEntradaTable.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.produtosEntradaTable.setRowSorter(produtoEntradaSorter);
		return this.produtosEntradaTable;
	}


	//barra de panel onde vai conter os botoes de atualizar dados dos produtos
	private JPanel stockProdutoEntradaUpdatePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel.setBackground(Tools.white);
		panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
				Tools.lightgrey, 40, 40, this::updateProdutoEntradaSetDados));
		return panel;
	}

	private JPanel stockProdutoSaidaViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(stockContentRetornPanel(), BorderLayout.NORTH);
		panel.add(stockProdutoSaidaViewContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todos produtos
	private JPanel stockProdutoSaidaViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.stockProdutoSaidaSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(produtoSaidaTable()));
		//panel.add(stockProdutoSaidaUpdatePanel(), BorderLayout.SOUTH);
		return panel;
	}

	private JTable produtoSaidaTable() {
		produtoSaidaSorter = new TableRowSorter<ProdutoSaidaAll>(produtosSaida);
		this.produtosSaidaTable = new JTable(produtosSaida);
		this.produtosSaidaTable.setRowHeight(30);
		//setColumnWidth(this.FuncionariosTable.getColumnModel().getColumn(8), 20);
		this.produtosSaidaTable.setRowSorter(produtoSaidaSorter);
		return this.produtosSaidaTable;
	}


	private JPanel stockEncomendaEfectuadaViewPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(stockContentRetornPanel(), BorderLayout.NORTH);
		panel.add(stockEncomendaEfectuadaViewContentPanel());
		return panel;
	}

	//aqui vai estar a tabela que vai mostrar todos produtos
	private JPanel stockEncomendaEfectuadaViewContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createSearchFieldPanel(this.stockEncomendaEfectuadaSearchField), BorderLayout.NORTH);
		panel.add(new JScrollPane(produtoEncomendaEfectuadaTable()));
		return panel;
	}

	private JTable produtoEncomendaEfectuadaTable() {
		encomendaEfectuadaSorter = new TableRowSorter<dal.EncomendaEfectuada>(encomendasEfectuadas);
		this.EncomendaEfectuadaTable = new JTable(encomendasEfectuadas);
		this.EncomendaEfectuadaTable.setRowHeight(30);
		this.EncomendaEfectuadaTable.setRowSorter(encomendaEfectuadaSorter);
		return this.EncomendaEfectuadaTable;
	}


	//barra de panel onde vai conter os botoes de atualizar dados dos produtos
	/*private JPanel stockProdutoSaidaUpdatePanel() {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panel.setBackground(Tools.white);
			panel.add(Tools.createButton("Atualizar dados", Tools.createIcon("/img/icons8_Restart_96px_1.png", 40), 
					Tools.lightgrey, 40, 40, null));
			panel.add(Tools.createButton("Apagar", Tools.createIcon("/img/icons8_Cancel_96px.png", 40), 
					Tools.lightgrey, 40, 40, null));
			return panel;
		}*/

	private JPanel stockRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showStockPanel), BorderLayout.WEST);
		return panel;
	}

	private JPanel stockContentRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showStockPanel), BorderLayout.WEST);
		return panel;
	}

	/*a parte  de stock  termina aqui </> :)*/


	/*a parte  da venda de  produto  começa aqui </> :)*/



	/*a parte  da venda de produto termina aqui </> :)*/

	//criar status panel onde vai conter o botao de recuar
	private JPanel optionRetornPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(reverseButton("", Tools.createIcon("/img/icons8_Back_104px.png", 30), 
				Tools.white, 30, 30, this::showMoreOptions), BorderLayout.WEST);
		return panel;
	}

	//criar o botão de retornar
	private JButton reverseButton(String text, Icon icon, Color color, int width, 
			int height, ActionListener listener) {
		JButton btn = Tools.createButton(text, icon, color, width, height, listener);
		return btn;
	}

	//criar o panel de pesquisa de dados
	private JPanel createSearchFieldPanel(JTextField field) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.setBorder(new EmptyBorder(10, 300, 5, 300));
		field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Tools.red));
		field.setFont(Tools.paragraph);
		panel.add(Tools.createLabel("", Tools.createIcon("/img/icons8_Search_100px.png", 30), null), BorderLayout.EAST);
		panel.add(field);
		return panel;
	}

	/*a parte de about(sobre) começa aqui*/

	private JTabbedPane aboutOption() {
		JTabbedPane tabbedPanel = new JTabbedPane();
		tabbedPanel.add(aboutPanel(), "Sobre");
		return tabbedPanel;
	}

	private JPanel aboutPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		aboutArea.setEditable(false);
		aboutArea.setFont(Tools.paragraph2);
		aboutArea.setMargin(new Insets(10, 10, 10, 10));
		aboutArea.setBackground(Tools.statisticColors[1]);
		aboutArea.append(ctrls.Document.readFile("about.txt"));
		panel.add(new JScrollPane(aboutArea));
		return panel;
	}

	/*a parte de about(sobre) termina aqui*/

	/*eventos referenciado this:: começa aqui*/
	/*refered listeners functions*/
	private void login(ActionEvent e) {
		String username = this.username.getText();
		String password = this.password.getText();

		if (!(password.length() != 0) || !(username.length() != 0)) {
			JOptionPane.showMessageDialog(this, "<html><p Color='red'>por favor preencha o nome de usuario ou a senha!!<p>"
					+ "<html>");

		}else {
			if (!Dbh.login(username, password)) {
				JOptionPane.showMessageDialog(this, "<html><p Color='red'>Usuario não autorizado<p>"
						+ "<html>");

			}else {

				if (Dbh.login(username, password, "Administrador")) {
					String usernameLabel = ctrls.Users.getUsername(username, password);
					BIfuncionario = ctrls.Users.getUserBI(username);
					this.username.setText("");
					this.password.setText("");
					createMenu();
					this.usernameLabel.setText(usernameLabel);
					this.EncomendaMoreOptionsReverse.setVisible(true);
					MainCL.show(RootPanel, "adminMain");
					navbarCL.show(navbar, "adminMainTop");
					cl.show(cardLayoutMainPanel, "estatistica");

				}else {
					String usernameLabel = ctrls.Users.getUsername(username, password);
					BIfuncionario = ctrls.Users.getUserBI(username);
					this.username.setText("");
					this.password.setText("");
					//createMenu();
					this.otherUsernameLabel.setText(usernameLabel);
					this.EncomendaMoreOptionsReverse.setVisible(false);
					MainCL.show(RootPanel, "adminMain");
					navbarCL.show(navbar, "otherMainTop");
					cl.show(cardLayoutMainPanel, "vender");
				}
			}
		}

	}

	private void logout(ActionEvent e) {
		setJMenuBar(null);
		MainCL.show(RootPanel, "login");
		this.username.requestFocus();
	}

	private void about(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "about");
	}

	private void exit(ActionEvent e) {
		if (JOptionPane.showConfirmDialog(this, "pretendes fechar o aplicativo?", "Info",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	private void showFuncionariosPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "funcionarios");
	}

	private void showFornecedoresPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "fornecedores");
	}

	private void showClientesPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "clientes");
	}

	private void showProdutosPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "produtos");
	}

	private void produtoAddOptionPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "addProdutoInfo");
	}

	private void addProdutoCategoria(ActionEvent e) {
		atualizarProdutoCategoria.setVisible(false);
		cadastrarProdutoCategoria.setVisible(true);
		cl.show(cardLayoutMainPanel, "addProdutoCategoria");
	}

	private void addProdutoTipo(ActionEvent e) {
		atualizarProdutoTipo.setVisible(false);
		cadastrarProdutoTipo.setVisible(true);
		cl.show(cardLayoutMainPanel, "addProdutoTipo");
	}

	private void addProduto(ActionEvent e) {
		atualizarProduto.setVisible(false);
		cadastrarProduto.setVisible(true);
		cl.show(cardLayoutMainPanel, "addProduto");
	}


	private void ProdutoViewOptionPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewProdutoInfo");
	}

	private void viewProdutos(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewProdutos");
	}

	private void showCategorias(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewCategorias");
	}

	private void showTipoProduto(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewTipoProdutos");
	}

	private void addFuncionarios(ActionEvent e) {
		atualizarFuncionario.setVisible(false);
		cadastrarFuncionario.setVisible(true);
		cl.show(cardLayoutMainPanel, "addFuncionarios");
	}

	private void viewFuncionarios(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewFuncionarios");
		funcionarios.fireTableChanged(null);
	}

	private void addFornecedores(ActionEvent e) {
		atualizarFornecedor.setVisible(false);
		cadastrarFornecedor.setVisible(true);
		cl.show(cardLayoutMainPanel, "addFornecedores");
	}

	private void viewFornecedores(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewFornecedores");
	}

	private void addClientes(ActionEvent e) {
		atualizarCliente.setVisible(false);
		cadastrarCliente.setVisible(true);
		cl.show(cardLayoutMainPanel, "addClientes");
	}

	private void viewClientes(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewClientes");
	}

	private void showMoreOptions(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "moreOptions");
	}

	private void vender(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "vender");
	}

	private void showUsersPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "usuarios");
	}

	private void addUsuarios(ActionEvent e) {
		atualizarUser.setVisible(false);
		cadastrarUser.setVisible(true);
		cl.show(cardLayoutMainPanel, "addUsuarios");
	}

	private void showEstatistica(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "estatistica");
	}

	private void viewUsers(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewUsuarios");
	}

	private void showVendasPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "vendas");
	}

	private void viewVendas(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewVendas");
	}

	private void viewVendaServico(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewVendaServico");
	}


	private void showPagamentosPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "pagamentos");
	}

	private void viewPagamentos(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewPagamentos");
	}

	private void showEquipamentosPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "equipamentos");
	}

	private void showEquipamentosAddMorePanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "equipamentoAddMoreOptionsPanel");
	}

	private void addEquipamentos(ActionEvent e) {
		atualizarEquipamento.setVisible(false);
		cadastrarEquipamento.setVisible(true);
		cl.show(cardLayoutMainPanel, "addEquipamentos");
	}

	private void addEquipamentoEstado(ActionEvent e) {
		atualizarEquipamentoEstado.setVisible(false);
		cadastrarEquipamentoEstado.setVisible(true);
		cl.show(cardLayoutMainPanel, "addEquipamentoEstado");
	}

	private void viewEquipamentos(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewEquipamentos");
	}

	private void viewEquipamentosInfo(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewEquipamentosInfo");
	}

	private void viewEquipamentosEstadoInfo(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewEquipamentosEstadoInfo");
	}


	private void showServicosPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "servicos");
	}

	private void addServico(ActionEvent e) {
		atualizarServico.setVisible(false);
		cadastrarServico.setVisible(true);
		cl.show(cardLayoutMainPanel, "addServicos");
	}

	private void viewServicos(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewServicos");
	}

	private void showEncomendasPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "encomendas");
	}

	private void encomendaAddOptionPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "addEncomendaInfo");
	}

	private void addEncomendaEstado(ActionEvent e) {
		atualizarEncomendaEstado.setVisible(false);
		cadastrarEncomendaEstado.setVisible(true);
		cl.show(cardLayoutMainPanel, "addEncomendaEstado");
	}

	private void addEncomendas(ActionEvent e) {
		atualizarEncomenda.setVisible(false);
		cadastrarEncomenda.setVisible(true);
		cl.show(cardLayoutMainPanel, "addEncomendas");
	}

	private void encomendaViewOptionPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewEncomendaInfo");
	}

	private void viewEncomendas(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewEncomendas");
	}

	private void viewEncomendaEstado(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewEncomendaEstado");
	}

	private void showStockPanel(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "stock");
	}

	private void viewStockProdutoEntrada(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewStockProdutoEntrada");
	}

	private void viewStockProdutoSaida(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewStockProdutoSaida");
	}

	private void viewStockProdutoEncomendaEfectuada(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "viewStockEncomendaEfectuada");
	}

	private void vendaProduto(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "vendaProduto");
	}

	private void vendaServico(ActionEvent e) {
		cl.show(cardLayoutMainPanel, "vendaServico");
	}

	private void addProdutoEntrada(ActionEvent e) {
		atualizarProdutoEntrada.setVisible(false);
		cadastrarProdutoEntrada.setVisible(true);
		cl.show(cardLayoutMainPanel, "addProdutoEntrada");
	}

	private void eliminarFuncionario(ActionEvent e) {
		int row = FuncionariosTable.getSelectedRow();
		String value = (String)FuncionariosTable.getValueAt(row, 0);
		if (JOptionPane.showConfirmDialog(this, "<html> <p>Pretendes Eliminar o registro?</p>" + "</html>",
				"Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {

				ctrls.Funcionario.Delete(value);
				funcionarios.removeRow(row);
				funcionarioCount.setText("" + ((Integer.parseInt(funcionarioCount.getText()) - 1)));
				funcionarios.fireTableDataChanged();

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

	}

	private void addFuncionario(ActionEvent e) {
		String bi = funcionarioBI.getText();
		String nome = funcionarioNome.getText();
		String sobrenome = funcionarioSobrenome.getText();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String cidade = funcionarioCidade.getText();
		String municipio = funcionarioMunicipio.getText();
		String bairro = funcionarioBairro.getText();
		String rua = funcionarioRua.getText();
		String sexo = null;

		if (funcionarioSexoM.isSelected()) {
			sexo = "M";
		}else if (funcionarioSexoF.isSelected()) {
			sexo = "F";
		}

		String email = funcionarioEmail.getText();

		if (bi.isEmpty() || nome.isEmpty() || sobrenome.isEmpty() || funcionarioTelefone.getText().isEmpty() || cidade.isEmpty() 
				|| municipio.isEmpty() || bairro.isEmpty() || email.isEmpty()) {
			JOptionPane.showMessageDialog(this, "<html><p>por favor preencha os campos obrigatorios</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
		}else {

			if (!(email.contains("@"))) {
				JOptionPane.showMessageDialog(this, "<html><p>o E-mail inserido é invalido</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
			}else if (!(email.contains("."))) {
				JOptionPane.showMessageDialog(this, "<html><p>o E-mail inserido é invalido</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
			}else {
				String data = df.format(funcionarioNascimento.getDate());
				int telefone = Integer.parseInt(funcionarioTelefone.getText());
				int id = ctrls.Funcao.SelectFuncaoId((String) funcionarioFuncao.getSelectedItem());

				ctrls.Funcionario.Insert(bi, nome, sobrenome, data, telefone, 
						cidade, municipio, bairro, rua, sexo, email, id);
				funcionarios.addRow(bi, nome, sobrenome, data.replace('/', '-'), 
						telefone, cidade, municipio, bairro, rua, sexo, 
						email, id, (String) funcionarioFuncao.getSelectedItem());
				userFuncionario.addItem((String)bi);

				funcionarioCount.setText("" + ((Integer.parseInt(funcionarioCount.getText()) + 1)));

				funcionarioBI.setText("");
				funcionarioNome.setText("");
				funcionarioSobrenome.setText("");
				funcionarioNascimento.setDateFormatString("");
				funcionarioTelefone.setText("");
				funcionarioCidade.setText("");
				funcionarioMunicipio.setText("");
				funcionarioBairro.setText("");
				funcionarioRua.setText("");
				funcionarioEmail.setText("");
				funcionarioBI.requestFocus();
			}

		}
		funcionarios.fireTableDataChanged();
	}

	private void updateFuncionario(ActionEvent e) {

		String bi = funcionarioBI.getText();
		String nome = funcionarioNome.getText();
		String sobrenome = funcionarioSobrenome.getText();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String cidade = funcionarioCidade.getText();
		String municipio = funcionarioMunicipio.getText();
		String bairro = funcionarioBairro.getText();
		String rua = funcionarioRua.getText();
		String sexo = null;

		if (funcionarioSexoM.isSelected()) {
			sexo = "M";
		}else if (funcionarioSexoF.isSelected()) {
			sexo = "F";
		}

		String email = funcionarioEmail.getText();
		String data = df.format(funcionarioNascimento.getDate());
		int telefone = Integer.parseInt(funcionarioTelefone.getText());
		int id = ctrls.Funcao.SelectFuncaoId((String) funcionarioFuncao.getSelectedItem());

		ctrls.Funcionario.Update(bi, nome, sobrenome, data, telefone, 
				cidade, municipio, bairro, rua, sexo, email, id);
		funcionarios.removeRow(selectedRow);
		funcionarios.addRow(bi, nome, sobrenome,data, 
				telefone, cidade, municipio, bairro, rua, sexo, 
				email, id, (String) funcionarioFuncao.getSelectedItem());
		
		userFuncionario.addItem((String)bi);

		funcionarioBI.setText("");
		funcionarioNome.setText("");
		funcionarioSobrenome.setText("");
		funcionarioNascimento.setDateFormatString("");
		funcionarioTelefone.setText("");
		funcionarioCidade.setText("");
		funcionarioMunicipio.setText("");
		funcionarioBairro.setText("");
		funcionarioRua.setText("");
		funcionarioEmail.setText("");
		funcionarioBI.requestFocus();
	}

	private void updateFuncionarioSetDados(ActionEvent e) {
		int row = FuncionariosTable.getSelectedRow();

		String bi = funcionarios.getValueAt(row, 0).toString();
		String nome = funcionarios.getValueAt(row, 1).toString();
		String sobrenome = funcionarios.getValueAt(row, 2).toString();
		String data = funcionarios.getValueAt(row, 3).toString();
		String telefone = funcionarios.getValueAt(row, 4).toString();
		String cidade = funcionarios.getValueAt(row, 5).toString();
		String municipio = funcionarios.getValueAt(row, 6).toString();
		String bairro = funcionarios.getValueAt(row, 7).toString();
		String rua = funcionarios.getValueAt(row, 8).toString();
		String sexo = funcionarios.getValueAt(row, 9).toString();
		String email = funcionarios.getValueAt(row, 10).toString();
		String funcao = funcionarios.getValueAt(row, 11).toString();

		//SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		funcionarioBI.setText(bi);
		funcionarioNome.setText(nome);
		funcionarioSobrenome.setText(sobrenome);
		funcionarioNascimento.setDateFormatString(data);
		funcionarioTelefone.setText(telefone);
		funcionarioCidade.setText(cidade);
		funcionarioMunicipio.setText(municipio);
		funcionarioBairro.setText(bairro);
		funcionarioRua.setText(rua);
		funcionarioEmail.setText(email);
		funcionarioFuncao.setSelectedItem((Object)funcao);
		if (sexo.equals("M")) {
			funcionarioSexoM.setSelected(true);
		}else if (sexo.equals("F")) {
			funcionarioSexoF.setSelected(true);
		}

		selectedRow = row;
		cadastrarFuncionario.setVisible(false);
		atualizarFuncionario.setVisible(true);
		cl.show(cardLayoutMainPanel, "addFuncionarios");
	}


	private void updateCliente(ActionEvent e) {

		String nome = clienteNome.getText();
		String cidade = clienteCidade.getText();
		String municipio = clienteMunicipio.getText();
		String bairro = clienteBairro.getText();
		String rua = clienteRua.getText();
		String pais = clientePais.getText();
		String sexo = null;

		if (clienteSexoM.isSelected()) {
			sexo = "M";
		}else if (clienteSexoF.isSelected()) {
			sexo = "F";
		}

		int telefone = Integer.parseInt(clienteTelefone.getText());
		clientes.removeRow(selectedRow);

		ctrls.Cliente.Update(this.id, nome, cidade, municipio, bairro, rua, pais, sexo, telefone);
		clientes.addRow(this.id, nome, cidade, municipio, bairro, rua, pais, sexo, telefone);


		clienteNome.setText("");
		clienteCidade.setText("");
		clienteMunicipio.setText("");
		clienteBairro.setText("");
		clienteRua.setText("");
		clientePais.setText("");
		clienteTelefone.setText("");
		clienteNome.requestFocus();
	}

	private void updateClienteSetDados(ActionEvent e) {
		int row = ClientesTable.getSelectedRow();

		this.id = (int)clientes.getValueAt(row, 0);
		String nome = clientes.getValueAt(row, 1).toString();
		String cidade = clientes.getValueAt(row, 2).toString();
		String municipio = clientes.getValueAt(row, 3).toString();
		String bairro = clientes.getValueAt(row, 4).toString();
		String rua = clientes.getValueAt(row, 5).toString();
		String pais = clientes.getValueAt(row, 6).toString();
		String sexo = clientes.getValueAt(row, 7).toString();
		String telefone = clientes.getValueAt(row, 8).toString();



		clienteNome.setText(nome);
		clienteCidade.setText(cidade);
		clienteMunicipio.setText(municipio);
		clienteBairro.setText(bairro);
		clienteRua.setText(rua);
		clientePais.setText(pais);
		clienteTelefone.setText(telefone);
		if (sexo.equals("M")) {
			clienteSexoM.setSelected(true);
		}else if (sexo.equals("F")) {
			clienteSexoF.setSelected(true);
		}

		selectedRow = row;
		cadastrarCliente.setVisible(false);
		atualizarCliente.setVisible(true);
		cl.show(cardLayoutMainPanel, "addClientes");
	}

	private void eliminarCliente(ActionEvent e) {
		int row = ClientesTable.getSelectedRow();
		int value = (int)ClientesTable.getValueAt(row, 0);
		if (JOptionPane.showConfirmDialog(this, "<html> <p>Pretendes Eliminar o registro?</p>" + "</html>",
				"Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {

				ctrls.Cliente.Delete(value);
				clientes.removeRow(row);
				clientCount.setText("" + ((Integer.parseInt(clientCount.getText()) - 1)));
				clientes.fireTableDataChanged();

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

	}

	private void addCliente(ActionEvent e) {
		String nome = clienteNome.getText();
		String cidade = clienteCidade.getText();
		String municipio = clienteMunicipio.getText();
		String bairro = clienteBairro.getText();
		String pais = clientePais.getText();
		String rua = clienteRua.getText();
		String sexo = null;

		if (clienteSexoM.isSelected()) {
			sexo = "M";
		}else if (clienteSexoF.isSelected()) {
			sexo = "F";
		}


		if (nome.isEmpty() ||  cidade.isEmpty() || municipio.isEmpty() || bairro.isEmpty() ||
				pais.isEmpty() || rua.isEmpty() || clienteTelefone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "<html><p>por favor preencha os campos obrigatorios</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
		}else {

			if (sexo == null) {
				JOptionPane.showMessageDialog(this, "<html><p>por favor selecione o sexo</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
			}else {
				int telefone = Integer.parseInt(clienteTelefone.getText());

				ctrls.Cliente.Insert(nome, cidade, municipio, bairro, 
						rua, pais, sexo, telefone);
				clientes.addRow(ctrls.Cliente.SelectLastId(), nome, cidade, municipio, bairro, 
						rua, pais, sexo, telefone);

				clientCount.setText("" + ((Integer.parseInt(clientCount.getText()) + 1)));
				this.encomendaCliente.addItem(nome);
				this.venderCliente.addItem(nome);
				clienteNome.setText("");
				clienteCidade.setText("");
				clienteMunicipio.setText("");
				clienteBairro.setText("");
				clientePais.setText("");
				clienteRua.setText("");
				clienteTelefone.setText("");
				clienteNome.requestFocus();
			}			
		}

		clientes.fireTableDataChanged();
	}

	private void updateFornecedor(ActionEvent e) {

		String nome = fornecedoreNome.getText();
		String cidade = fornecedoreCidade.getText();
		String municipio = fornecedoreMunicipio.getText();
		String bairro = fornecedoreBairro.getText();
		String rua = fornecedoreRua.getText();
		String pais = fornecedorePais.getText();

		int telefone = Integer.parseInt(fornecedoreTelefone.getText());

		ctrls.Fornecedor.Update(this.id, nome, cidade, municipio, bairro, rua, pais, telefone);
		fornecedores.removeRow(selectedRow);
		fornecedores.addRow(this.id, nome, cidade, municipio, bairro, rua, pais, telefone);
		ctrls.Fornecedor.Update(this.id, nome, cidade, municipio, bairro, rua, pais, telefone);

		fornecedoreNome.setText("");
		fornecedoreCidade.setText("");
		fornecedoreMunicipio.setText("");
		fornecedoreBairro.setText("");
		fornecedoreRua.setText("");
		fornecedorePais.setText("");
		fornecedoreTelefone.setText("");
		fornecedoreNome.requestFocus();
	}

	private void updateFornecedorSetDados(ActionEvent e) {
		int row = FornecedoresTable.getSelectedRow();

		this.id = (int)FornecedoresTable.getValueAt(row, 0);
		String nome = FornecedoresTable.getValueAt(row, 1).toString();
		String cidade = FornecedoresTable.getValueAt(row, 2).toString();
		String municipio = FornecedoresTable.getValueAt(row, 3).toString();
		String bairro = FornecedoresTable.getValueAt(row, 4).toString();
		String pais = FornecedoresTable.getValueAt(row, 5).toString();
		String rua = FornecedoresTable.getValueAt(row, 6).toString();
		String telefone = FornecedoresTable.getValueAt(row, 7).toString();

		fornecedoreNome.setText(nome);
		fornecedoreCidade.setText(cidade);
		fornecedoreMunicipio.setText(municipio);
		fornecedoreBairro.setText(bairro);
		fornecedorePais.setText(pais);
		fornecedoreRua.setText(rua);
		fornecedoreTelefone.setText(telefone);

		selectedRow = row;
		cadastrarFornecedor.setVisible(false);
		atualizarFornecedor.setVisible(true);
		cl.show(cardLayoutMainPanel, "addFornecedores");
	}

	private void eliminarFornecedor(ActionEvent e) {
		int row = FornecedoresTable.getSelectedRow();
		int value = (int)FornecedoresTable.getValueAt(row, 0);
		if (JOptionPane.showConfirmDialog(this, "<html> <p>Pretendes Eliminar o registro?</p>" + "</html>",
				"Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {

				ctrls.Fornecedor.Delete(value);
				fornecedores.removeRow(row);
				fornecedorCount.setText("" + ((Integer.parseInt(fornecedorCount.getText()) - 1)));
				fornecedores.fireTableDataChanged();

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

	}

	private void addFornecedor(ActionEvent e) {
		String nome = fornecedoreNome.getText();
		String cidade = fornecedoreCidade.getText();
		String municipio = fornecedoreMunicipio.getText();
		String bairro = fornecedoreBairro.getText();
		String pais = fornecedorePais.getText();
		String rua = fornecedoreRua.getText();


		if (nome.isEmpty() ||  cidade.isEmpty() || municipio.isEmpty() || bairro.isEmpty() ||
				pais.isEmpty() || rua.isEmpty() || fornecedoreTelefone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "<html><p>por favor preencha os campos obrigatorios</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
		}else {

			int telefone = Integer.parseInt(fornecedoreTelefone.getText());

			ctrls.Fornecedor.Insert(nome, cidade, municipio, bairro, 
					pais, rua, telefone);
			fornecedores.addRow(ctrls.Fornecedor.SelectLastId(), nome, cidade, municipio, bairro, 
					pais, rua, telefone);
			fornecedorCount.setText("" + ((Integer.parseInt(fornecedorCount.getText()) + 1)));
			produtoEntradaFornecedor.addItem(nome);
			fornecedoreNome.setText("");
			fornecedoreCidade.setText("");
			fornecedoreMunicipio.setText("");
			fornecedoreBairro.setText("");
			fornecedorePais.setText("");
			fornecedoreRua.setText("");
			fornecedoreTelefone.setText("");
			fornecedoreNome.requestFocus();			
		}

		fornecedores.fireTableDataChanged();
	}

	private void updateProduto(ActionEvent e) {

		float preco = Float.parseFloat(produtoPreco.getText());
		int quantidade = Integer.parseInt(produtoQuantidade.getText());

		int categoriaId = ctrls.Produto.SelectCategoriaId((String) produtoCategoria.getSelectedItem());
		int tipoId = ctrls.Produto.SelectTipoId((String) produtoTipo.getSelectedItem());

		ctrls.Produto.Update(this.id, preco, quantidade, categoriaId, tipoId);
		produtos.removeRow(selectedRow);
		produtos.addRow(this.id, preco, quantidade, categoriaId, (String)produtoCategoria.getSelectedItem(), 
				tipoId, (String)produtoTipo.getSelectedItem());

		produtoPreco.setText("");
		produtoQuantidade.setText("");
		produtoPreco.requestFocus();
	}

	private void updateProdutoSetDados(ActionEvent e) {
		int row = ProdutosTable.getSelectedRow();

		this.id = (int)ProdutosTable.getValueAt(row, 0);
		String preco = ProdutosTable.getValueAt(row, 1).toString();
		String quantidade = ProdutosTable.getValueAt(row, 2).toString();
		String categoria = ProdutosTable.getValueAt(row, 3).toString();
		String tipo = ProdutosTable.getValueAt(row, 4).toString();

		produtoPreco.setText(preco.substring(0, preco.length()-2));
		produtoQuantidade.setText(quantidade);
		produtoCategoria.setSelectedItem((Object)categoria);
		produtoCategoria.setSelectedItem((Object)tipo);

		selectedRow = row;
		cadastrarProduto.setVisible(false);
		atualizarProduto.setVisible(true);
		cl.show(cardLayoutMainPanel, "addProduto");
	}

	private void eliminarProduto(ActionEvent e) {
		int row = ProdutosTable.getSelectedRow();
		int value = (int)ProdutosTable.getValueAt(row, 0);
		String categoria = (String)ProdutosTable.getValueAt(row, 3);
		if (JOptionPane.showConfirmDialog(this, "<html> <p>Pretendes Eliminar o registro?</p>" + "</html>",
				"Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {

				ctrls.Produto.Delete(value);
				this.venderProduto.removeItem(categoria);
				this.produtoEntradaProduto.removeItem(categoria);
				produtos.removeRow(row);
				produtos.fireTableDataChanged();

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

	}


	private void addProdutoInsert(ActionEvent e) {

		if (produtoPreco.getText().isEmpty() ||   produtoQuantidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "<html><p>por favor preencha os campos obrigatorios</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
		}else {
			float preco = Float.parseFloat(produtoPreco.getText().contains(",") ? produtoPreco.getText().replace(',', '.') 
					: produtoPreco.getText());
			//int quantidade = Integer.parseInt(produtoQuantidade.getText());
			
			if (!(preco > 0)) {
				JOptionPane.showMessageDialog(this, "<html><p>preço " + preco + " inaceitavel</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
			}else {
				int categoriaId = ctrls.Produto.SelectCategoriaId((String) produtoCategoria.getSelectedItem());
				int tipoId = ctrls.Produto.SelectTipoId((String) produtoTipo.getSelectedItem());

				ctrls.Produto.Insert(preco, 0, categoriaId, tipoId);
				produtoEntradaProduto.addItem((String)produtoCategoria.getSelectedItem());
				venderProduto.addItem((String)produtoCategoria.getSelectedItem());
				encomendaProduto.addItem((String) produtoCategoria.getSelectedItem());
				produtos.addRow(ctrls.Produto.SelectLastId(), preco, 0, categoriaId,
						(String) produtoCategoria.getSelectedItem(), tipoId, (String) produtoTipo.getSelectedItem());


				produtoPreco.setText("");
				produtoPreco.requestFocus();
			}
			
		}

		produtos.fireTableDataChanged();
	}




	private void eliminarProdutoCategoria(ActionEvent e) {
		int row = ProdutoCategoriasTable.getSelectedRow();
		int value = (int)ProdutoCategoriasTable.getValueAt(row, 0);
		if (JOptionPane.showConfirmDialog(this, "<html> <p>Pretendes Eliminar o registro?</p>" + "</html>",
				"Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {

				ctrls.Categoria.Delete(value);
				categorias.removeRow(row);
				categorias.fireTableDataChanged();

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

	}

	private void updateProdutoCategoria(ActionEvent e) {

		String nome = produtoCategoriaNome.getText();

		ctrls.Categoria.Update(this.id, nome);
		categorias.removeRow(selectedRow);
		categorias.addRow(this.id, nome);

		produtoCategoriaNome.setText("");
		produtoCategoriaNome.requestFocus();
	}

	private void updateProdutoCategoriaSetDados(ActionEvent e) {
		int row = ProdutoCategoriasTable.getSelectedRow();

		this.id = (int)ProdutoCategoriasTable.getValueAt(row, 0);
		String nome = ProdutoCategoriasTable.getValueAt(row, 1).toString();
		produtoCategoriaNome.setText(nome);

		selectedRow = row;
		cadastrarProdutoCategoria.setVisible(false);
		atualizarProdutoCategoria.setVisible(true);
		cl.show(cardLayoutMainPanel, "addProdutoCategoria");
	}


	private void addProdutoCategoriaInsert(ActionEvent e) {

		String categoria = produtoCategoriaNome.getText();

		if (categoria.isEmpty()) {
			JOptionPane.showMessageDialog(this, "<html><p>por favor preencha os campos obrigatorios</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
		}else {

			ctrls.Categoria.Insert(categoria);
			produtoCategoria.addItem(categoria);
			categorias.addRow(ctrls.Categoria.SelectLastId(), categoria);

			produtoCategoriaNome.setText("");
			produtoCategoriaNome.requestFocus();			
		}

		categorias.fireTableDataChanged();
	}


	private void updateProdutoTipo(ActionEvent e) {

		String nome = produtoTipoNome.getText();

		ctrls.TipoProduto.Update(this.id, nome);
		tipos.removeRow(selectedRow);
		tipos.addRow(this.id, nome);

		produtoTipoNome.setText("");
		produtoTipoNome.requestFocus();
	}

	private void updateProdutoTipoSetDados(ActionEvent e) {
		int row = ProdutoTiposTable.getSelectedRow();

		this.id = (int)ProdutoTiposTable.getValueAt(row, 0);
		String nome = ProdutoTiposTable.getValueAt(row, 1).toString();
		produtoTipoNome.setText(nome);

		selectedRow = row;
		cadastrarProdutoTipo.setVisible(false);
		atualizarProdutoTipo.setVisible(true);
		cl.show(cardLayoutMainPanel, "addProdutoTipo");
	}


	private void eliminarProdutoTipo(ActionEvent e) {
		int row = ProdutoTiposTable.getSelectedRow();
		int value = (int)ProdutoTiposTable.getValueAt(row, 0);
		if (JOptionPane.showConfirmDialog(this, "<html> <p>Pretendes Eliminar o registro?</p>" + "</html>",
				"Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {

				ctrls.TipoProduto.Delete(value);
				tipos.removeRow(row);
				tipos.fireTableDataChanged();

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

	}


	private void addProdutoTipoProduto(ActionEvent e) {

		String tipo = produtoTipoNome.getText();

		if (tipo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "<html><p>por favor preencha os campos obrigatorios</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
		}else {

			ctrls.TipoProduto.Insert(tipo);
			tipos.addRow(ctrls.TipoProduto.SelectLastId(), tipo);
			produtoTipo.addItem(tipo);
			produtoTipoNome.setText("");
			produtoTipoNome.requestFocus();			
		}

		tipos.fireTableDataChanged();
	}

	private void updateUser(ActionEvent e) {

		String username = userUsername.getText();
		String password = userPassword.getText();

		ctrls.Users.Update((String) userFuncionario.getSelectedItem(), username, password);
		users.removeRow(selectedRow);
		users.addRow((String) userFuncionario.getSelectedItem(), username, password);
		userUsername.setText("");
		userPassword.setText("");
		userUsername.requestFocus();
	}

	private void updateUserSetDados(ActionEvent e) {
		int row = UsersTable.getSelectedRow();

		String bi = UsersTable.getValueAt(row, 0).toString();
		String username = UsersTable.getValueAt(row, 1).toString();
		String password = UsersTable.getValueAt(row, 2).toString();

		userFuncionario.setSelectedItem((Object)bi);
		userUsername.setText(username);
		userPassword.setText(password);

		selectedRow = row;
		cadastrarUser.setVisible(false);
		atualizarUser.setVisible(true);
		cl.show(cardLayoutMainPanel, "addUsuarios");
	}

	private void eliminarUser(ActionEvent e) {
		int row = UsersTable.getSelectedRow();
		String value = (String)UsersTable.getValueAt(row, 0);
		if (JOptionPane.showConfirmDialog(this, "<html> <p>Pretendes Eliminar o registro?</p>" + "</html>",
				"Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {

				ctrls.Users.Delete(value);
				users.removeRow(row);
				users.fireTableDataChanged();

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

	}

	private void addUser(ActionEvent e) {

		String username = userUsername.getText();
		String password = userPassword.getText();

		if (username.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(this, "<html><p>por favor preencha os campos obrigatorios</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
		}else {

			ctrls.Users.Insert((String)userFuncionario.getSelectedItem(), username, password);
			users.addRow((String)userFuncionario.getSelectedItem(), username, password);

			userUsername.setText("");
			userPassword.setText("");
			produtoCategoriaNome.requestFocus();			
		}

		users.fireTableDataChanged();
	}


	private void addEquipamento(ActionEvent e) {

		String nome = equipamentoNome.getText();

		if (nome.isEmpty()) {
			JOptionPane.showMessageDialog(this, "<html><p>por favor preencha os campos obrigatorios</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
		}else {



			int estadoId = ctrls.EstadoEquipamento.SelectEstadoId((String) equipamentoEstado.getSelectedItem());
			ctrls.Equipamento.Insert(nome, estadoId);
			equipamentos.addRow(ctrls.Equipamento.SelectLastId(), nome, (String) equipamentoEstado.getSelectedItem());

			equipamentoNome.setText("");
			equipamentoNome.requestFocus();			
		}

		equipamentos.fireTableDataChanged();
	}

	private void updateEquipamento(ActionEvent e) {

		String nome = equipamentoNome.getText();

		int estado = ctrls.EstadoEquipamento.SelectEstadoId((String) equipamentoEstado.getSelectedItem());

		ctrls.Equipamento.Update(this.id, estado, nome);
		equipamentos.removeRow(selectedRow);
		equipamentos.addRow(id, nome, (String) equipamentoEstado.getSelectedItem());

		equipamentoNome.setText("");
		equipamentoNome.requestFocus();
	}

	private void updateEquipamentoSetDados(ActionEvent e) {
		int row = EquipamentosTable.getSelectedRow();

		this.id = (int)EquipamentosTable.getValueAt(row, 0);
		String nome = EquipamentosTable.getValueAt(row, 1).toString();
		String estado = EquipamentosTable.getValueAt(row, 2).toString();

		equipamentoNome.setText(nome);
		equipamentoEstado.setSelectedItem((Object)estado);

		selectedRow = row;
		cadastrarEquipamento.setVisible(false);
		atualizarEquipamento.setVisible(true);
		cl.show(cardLayoutMainPanel, "addEquipamentos");
	}

	private void eliminarEquipamento(ActionEvent e) {
		int row = EquipamentosTable.getSelectedRow();
		int value = (int)EquipamentosTable.getValueAt(row, 0);
		if (JOptionPane.showConfirmDialog(this, "<html> <p>Pretendes Eliminar o registro?</p>" + "</html>",
				"Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {

				ctrls.Equipamento.Delete(value);
				equipamentos.removeRow(row);
				equipamentos.fireTableDataChanged();

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}


	}


	private void addEquipamentoEstadoInsert(ActionEvent e) {

		String nome = equipamentoEstadoNome.getText();

		if (nome.isEmpty()) {
			JOptionPane.showMessageDialog(this, "<html><p>por favor preencha os campos obrigatorios</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
		}else {

			ctrls.EstadoEquipamento.Insert(nome);
			estadosEquipamento.addRow(ctrls.EstadoEquipamento.SelectLastId(), nome);

			equipamentoEstadoNome.setText("");
			equipamentoEstadoNome.requestFocus();			
		}

		estadosEquipamento.fireTableDataChanged();
	}

	private void updateEquipamentoEstado(ActionEvent e) {

		String nome = equipamentoEstadoNome.getText();

		ctrls.EstadoEquipamento.Update(this.id, nome);
		estadosEquipamento.removeRow(selectedRow);
		estadosEquipamento.addRow(this.id, nome);

		equipamentoEstadoNome.setText("");
		equipamentoEstadoNome.requestFocus();
	}

	private void updateEquipamentoEstadoSetDados(ActionEvent e) {
		int row = EquipamentosEstadoTable.getSelectedRow();

		this.id = (int)EquipamentosEstadoTable.getValueAt(row, 0);
		String nome = EquipamentosEstadoTable.getValueAt(row, 1).toString();
		equipamentoEstadoNome.setText(nome);

		selectedRow = row;
		cadastrarEquipamentoEstado.setVisible(false);
		atualizarEquipamentoEstado.setVisible(true);
		cl.show(cardLayoutMainPanel, "addEquipamentoEstado");
	}

	private void eliminarEquipamentoEstado(ActionEvent e) {
		int row = EquipamentosEstadoTable.getSelectedRow();
		int value = (int)EquipamentosEstadoTable.getValueAt(row, 0);
		if (JOptionPane.showConfirmDialog(this, "<html> <p>Pretendes Eliminar o registro?</p>" + "</html>",
				"Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {

				ctrls.EstadoEquipamento.Delete(value);
				estadosEquipamento.removeRow(row);
				estadosEquipamento.fireTableDataChanged();

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}


	}


	private void addServicoInsert(ActionEvent e) {

		String nome = servicoNome.getText();

		if (nome.isEmpty()) {
			JOptionPane.showMessageDialog(this, "<html><p>por favor preencha os campos obrigatorios</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
		}else {

			ctrls.Servico.Insert(nome);
			servicos.addRow(ctrls.Servico.SelectLastId(), nome);
			encomendaServico.addItem(nome);
			servicoNome.setText("");
			servicoNome.requestFocus();			
		}

		servicos.fireTableDataChanged();
	}

	private void updateServico(ActionEvent e) {

		String nome = servicoNome.getText();

		ctrls.Servico.Update(this.id, nome);
		servicos.removeRow(selectedRow);
		servicos.addRow(this.id, nome);

		servicoNome.setText("");
		servicoNome.requestFocus();
	}

	private void updateServicoSetDados(ActionEvent e) {
		int row = ServicosTable.getSelectedRow();

		this.id = (int)ServicosTable.getValueAt(row, 0);
		String nome = ServicosTable.getValueAt(row, 1).toString();
		servicoNome.setText(nome);

		selectedRow = row;
		cadastrarServico.setVisible(false);
		atualizarServico.setVisible(true);
		cl.show(cardLayoutMainPanel, "addServicos");
	}

	private void eliminarServico(ActionEvent e) {
		int row = ServicosTable.getSelectedRow();
		int value = (int)ServicosTable.getValueAt(row, 0);
		if (JOptionPane.showConfirmDialog(this, "<html> <p>Pretendes Eliminar o registro?</p>" + "</html>",
				"Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {

				ctrls.Servico.Delete(value);
				servicos.removeRow(row);
				servicos.fireTableDataChanged();

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}


	}


	private void addEstadoEncomenda(ActionEvent e) {

		String nome = encomendaEstadoNome.getText();

		if (nome.isEmpty()) {
			JOptionPane.showMessageDialog(this, "<html><p>por favor preencha os campos obrigatorios</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
		}else {

			ctrls.EncomendaEstado.Insert(nome);
			estadosEncomenda.addRow(ctrls.Servico.SelectLastId(), nome);

			encomendaEstadoNome.setText("");
			encomendaEstadoNome.requestFocus();			
		}

		estadosEncomenda.fireTableDataChanged();
	}

	private void updateEstadoEncomenda(ActionEvent e) {

		String nome = encomendaEstadoNome.getText();

		ctrls.EncomendaEstado.Update(this.id, nome);
		estadosEncomenda.removeRow(selectedRow);
		estadosEncomenda.addRow(this.id, nome);

		encomendaEstadoNome.setText("");
		encomendaEstadoNome.requestFocus();
	}

	private void updateEstadoEncomendaSetDados(ActionEvent e) {
		int row = EncomendasEstadoTable.getSelectedRow();

		this.id = (int)EncomendasEstadoTable.getValueAt(row, 0);
		String nome = EncomendasEstadoTable.getValueAt(row, 1).toString();
		encomendaEstadoNome.setText(nome);

		selectedRow = row;
		cadastrarEncomendaEstado.setVisible(false);
		atualizarEncomendaEstado.setVisible(true);
		cl.show(cardLayoutMainPanel, "addEncomendaEstado");
	}

	private void eliminarEstadoEncomenda(ActionEvent e) {
		int row = EncomendasEstadoTable.getSelectedRow();
		int value = (int)EncomendasEstadoTable.getValueAt(row, 0);
		if (JOptionPane.showConfirmDialog(this, "<html> <p>Pretendes Eliminar o registro?</p>" + "</html>",
				"Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {

				ctrls.EncomendaEstado.Delete(value);
				estadosEncomenda.removeRow(row);
				estadosEncomenda.fireTableDataChanged();

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}


	}


	private void updateProdutoEntrada(ActionEvent e) {

		float preco = Float.parseFloat(produtoPreco.getText());
		int quantidade = Integer.parseInt(produtoQuantidade.getText());

		int categoriaId = ctrls.Produto.SelectCategoriaId((String) produtoCategoria.getSelectedItem());
		int tipoId = ctrls.Produto.SelectTipoId((String) produtoTipo.getSelectedItem());

		ctrls.Produto.Update(this.id, preco, quantidade, categoriaId, tipoId);
		produtos.removeRow(selectedRow);
		produtos.addRow(this.id, preco, quantidade, categoriaId, (String)produtoCategoria.getSelectedItem(), 
				tipoId, (String)produtoTipo.getSelectedItem());

		produtoPreco.setText("");
		produtoQuantidade.setText("");
		produtoPreco.requestFocus();
	}

	private void updateProdutoEntradaSetDados(ActionEvent e) {
		int row = produtosEntradaTable.getSelectedRow();

		this.id = (int)produtosEntradaTable.getValueAt(row, 0);
		String fornecedor = produtosEntradaTable.getValueAt(row, 1).toString();
		String produto = produtosEntradaTable.getValueAt(row, 2).toString();
		int quantidade = (int)produtosEntradaTable.getValueAt(row, 3);
		String data = produtosEntradaTable.getValueAt(row, 3).toString();

		//produtoEntradaData.set
		produtoEntradaQuantidade.setText("" + quantidade);
		produtoEntradaFornecedor.setSelectedItem((Object)fornecedor);
		produtoEntradaProduto.setSelectedItem((Object)produto);

		selectedRow = row;
		cadastrarProdutoEntrada.setVisible(false);
		atualizarProdutoEntrada.setVisible(true);
		cl.show(cardLayoutMainPanel, "addProdutoEntrada");
	}

	private void addProdutoEntradaInsert(ActionEvent e) {

		if (produtoEntradaQuantidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "<html><p>por favor preencha os campos obrigatorios</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
		}else {

			int quantidade = Integer.parseInt(produtoEntradaQuantidade.getText());
			
			if (!(quantidade > 0)) {
				JOptionPane.showMessageDialog(this, "<html><p>quantidade " + quantidade + " inaceitavel</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
			}else {
				String fornecedor = (String) produtoEntradaFornecedor.getSelectedItem();
				String produto = (String)produtoEntradaProduto.getSelectedItem();
			

				int fornecedorId = ctrls.Fornecedor.SelectfornecedorId(fornecedor);
				int produtoCategoriaId = ctrls.Produto.SelectCategoriaId(produto);
				int produtoId = ctrls.Produto.SelectProdutoId(produtoCategoriaId);

				DateFormat df = new SimpleDateFormat("yyy/MM/dd");
				String data = df.format(produtoEntradaData.getDate() != null ? produtoEntradaData.getDate() : new java.util.Date());

				ctrls.ProdutoEntrada.Insert(data, quantidade, fornecedorId, produtoId);
				produtos.appendValue(produtoId, quantidade);
				
				if (produtosEntrada.exists(fornecedor, produto, data.replace('/', '-'), quantidade)) {
					produtosEntrada.append(fornecedor, produto, data.replace('/', '-'), quantidade);
				}else {
					produtosEntrada.addRow(ctrls.ProdutoEntrada.SelectLastId(), data.replace('/', '-'), quantidade, fornecedor, 
							produto);
				}
				

				produtoEntradaQuantidade.setText("");
			}
	
		}

		produtos.fireTableDataChanged(); 
		produtosEntrada.fireTableDataChanged();
	}

	private void addEncomenda(ActionEvent e) {
		String servico = (String)encomendaServico.getSelectedItem();
		String estado = (String)encomendaEstado.getSelectedItem();
		String cliente = (String)encomendaCliente.getSelectedItem();
		String quantidade = encomendaQuantidade.getText();
		String produtoCategoria = (String)encomendaProduto.getSelectedItem();

		int servicoId = ctrls.Servico.SelectServicoId(servico);
		int estadoId = ctrls.EncomendaEstado.SelectEstadoEncomendaId(estado);
		int clienteId = ctrls.Cliente.SelectClienteId(cliente);
		int produtoCategoriaId = ctrls.Produto.SelectCategoriaId(produtoCategoria);
		int idProduto = ctrls.Produto.SelectProdutoId(produtoCategoriaId);
		if (quantidade.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor insira a quantidade necessaria!");
		}else {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			String date = df.format(encomendaData.getDate() != null ? encomendaData.getDate() : new java.util.Date());
			int quantidadeValue = Integer.parseInt(quantidade);
			
			if (!(quantidadeValue > 0)) {
				JOptionPane.showMessageDialog(this, "<html><p>quantidade " + quantidade + " inaceitavel</p></html>", "Alerta!", JOptionPane.WARNING_MESSAGE);
			}else {
				if (ctrls.Encomenda.insertEncomenda(servicoId, estadoId, clienteId, produtoCategoriaId, quantidadeValue, date)) {
					produtos.decreValue(idProduto, quantidadeValue);

					if (encomendas.exists(produtoCategoria, cliente, estado)) {
						encomendas.appendValue(produtoCategoria, cliente, estado, quantidadeValue);
					}else {
						encomendas.addRow(ctrls.Encomenda.SelectLastId(), servico, estado, cliente, produtoCategoria, quantidadeValue, date.replace('/', '-'));
						encomendaCount.setText("" + ((Integer.parseInt(encomendaCount.getText()) + 1)));
					}

					for (int i = 0; i < this.venderServicoCliente.getItemCount(); i++) {

						if (!(this.venderServicoCliente.getItemAt(i).equals(cliente))) {
							this.venderServicoCliente.removeItem((String)cliente);
							this.venderServicoCliente.addItem(cliente);
						}

					}

					JOptionPane.showMessageDialog(null, "Encomenda Adicionada Com Successo!");
				}else {
					JOptionPane.showMessageDialog(null, "Quantidade inexistente em stock!");
				}
			}
			
		}

		encomendas.fireTableDataChanged();
	}

	private void eliminarEncomenda(ActionEvent e) {
		int row = encomendasTable.getSelectedRow();
		int idEncomenda = (int)encomendasTable.getValueAt(row, 0);
		String categoria = (String)encomendasTable.getValueAt(row, 4);
		int idCategoria = ctrls.Produto.SelectCategoriaId(categoria);
		int quantidade = (int)encomendasTable.getValueAt(row, 5);
		int idProduto = ctrls.Produto.SelectProdutoId(idCategoria);
		if (JOptionPane.showConfirmDialog(this, "<html> <p>Pretendes Eliminar o registro?</p>" + "</html>",
				"Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {

				ctrls.Encomenda.Delete(idEncomenda, idCategoria);
				produtos.appendValueFromEncomenda(idProduto, quantidade);
				encomendas.removeRow(row);
				encomendaCount.setText("" + encomendas.count());
				encomendas.fireTableDataChanged();
				produtos.fireTableDataChanged();

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}


	}


	/*eventos referenciado this:: termina aqui </>:)*/

	/*other events*/
	private void addListeners() {

		this.username.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					password.requestFocus();
				}else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					username.setText("");
				}
			}
		});

		this.password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login(null);
				}else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					password.setText("");
				}
			}
		});


		funcionarioSearchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(funcionarioSearchField, funcionariosFilter, funcionariosSorter);
			}

		});

		fornecedoreSearchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(fornecedoreSearchField, fornecedoresFilter, fornecedoresSorter);
			}

		});

		clienteSearchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(clienteSearchField, clientesFilter, clientesSorter);
			}

		});


		produtoSearchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(produtoSearchField, produtosFilter, produtosSorter);
			}

		});

		categoriaSearchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(categoriaSearchField, produtosCategoriaFilter, produtosCategoriaSorter);
			}

		});

		tipoProdutoSearchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(tipoProdutoSearchField, produtosTipoFilter, produtosTipoSorter);
			}

		});


		userSearchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(userSearchField, usersFilter, usersSorter);
			}

		});

		pagamentoSearchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(pagamentoSearchField, pagamentosFilter, pagamentosSorter);
			}

		});

		equipamentoSearchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(equipamentoSearchField, equipamentosFilter, equipamentosSorter);
			}

		});

		equipamentoEstadoSearchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(equipamentoEstadoSearchField, equipamentosEstadoFilter, equipamentosEstadoSorter);
			}

		});


		servicoSearchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(servicoSearchField, servicosFilter, servicosSorter);
			}

		});

		encomendaEstadoSearchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(encomendaEstadoSearchField, encomendaEstadoFilter, encomendaEstadoSorter);
			}

		});

		encomendaSearchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(encomendaSearchField, encomendaFilter, encomendaSorter);
			}

		});

		vendaSearchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(vendaSearchField, vendaFilter, vendaSorter);
			}

		});

		stockProdutoSaidaSearchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(stockProdutoSaidaSearchField, produtoSaidaFilter, produtoSaidaSorter);
			}
		});

		stockEncomendaEfectuadaSearchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(stockEncomendaEfectuadaSearchField, encomendaEfectuadaFilter, encomendaEfectuadaSorter);
			}
		});

		stockProdutoEntradaSearchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(stockProdutoEntradaSearchField, produtoEntradaFilter, produtoEntradaSorter);
			}
		});

		vendaServicoSearchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Tools.searchTableContent(vendaServicoSearchField, vendaServicoFilter, vendaServicoSorter);
			}
		});



	}

	/*função personalizada pra poder usar layout nulo(null)*/
	private void addComponent(Container container, JComponent component, int x, int y,
			int width, int height) {
		component.setBounds(x, y, width, height);
		container.add(component);
	}

	/* loading window timer*/
	private void setTimer() {
		Timer timer = new Timer(100, new ActionListener() {
			int i = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(i);
				i+=1;

				if (i == 25) {
					progressBar.setForeground(new Color(22, 125, 177));
				}else if(i == 50) {
					progressBar.setForeground(new Color(244, 155, 52));
				}else if(i == 75) {
					progressBar.setForeground(new Color(236, 112, 67));
				}else if (i == 100) {
					((Timer)e.getSource()).stop();
					MainCL.show(RootPanel, "login");
					username.requestFocus();
				} 
			}
		});

		timer.start();
	}


	//a função main ela é que chama a tela do admin
	public static void main(String[] args) {	
		SwingUtilities.invokeLater(() -> {
			new AdminFrame().setVisible(true);
		}); 
	}
}
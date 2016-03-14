
import java.awt.BorderLayout;

import java.awt.Container;

import java.awt.GridLayout;

import java.awt.Color;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

 

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

 

public class LinkGame implements ActionListener {

JFrame mainFrame; // �����

Container thisContainer;

JPanel centerPanel, southPanel, northPanel; // �����

JButton diamondsButton[][] = new JButton[6][5];// ��Ϸ��ť����

JButton exitButton, resetButton, newlyButton; // �˳������У����¿�ʼ��ť

JLabel fractionLable = new JLabel("0"); // ������ǩ

JButton firstButton, secondButton; // �ֱ��¼���α�ѡ�еİ�ť

int grid[][] = new int[8][7];// ������Ϸ��ťλ��

static boolean pressInformation = false; // �ж��Ƿ��а�ť��ѡ��

int x0 = 0, y0 = 0, x = 0, y = 0, firstMsg = 0, secondMsg = 0, validateLV; // ��Ϸ��ť��λ������

int i, j, k, n;// ������������

 

public void init() {

mainFrame = new JFrame("JKJ������");

thisContainer = mainFrame.getContentPane();

thisContainer.setLayout(new BorderLayout());

centerPanel = new JPanel();

southPanel = new JPanel();

northPanel = new JPanel();

thisContainer.add(centerPanel, "Center");

thisContainer.add(southPanel, "South");

thisContainer.add(northPanel, "North");

centerPanel.setLayout(new GridLayout(6, 5));

for (int cols = 0; cols < 6; cols++) {

for (int rows = 0; rows < 5; rows++) {

diamondsButton[cols][rows] = new JButton(String

.valueOf(grid[cols + 1][rows + 1]));

diamondsButton[cols][rows].addActionListener(this);

centerPanel.add(diamondsButton[cols][rows]);

}

}

exitButton = new JButton("�˳�");

exitButton.addActionListener(this);

resetButton = new JButton("����");

resetButton.addActionListener(this);

newlyButton = new JButton("����һ��");

newlyButton.addActionListener(this);

southPanel.add(exitButton);

southPanel.add(resetButton);

southPanel.add(newlyButton);

fractionLable.setText(String.valueOf(Integer.parseInt(fractionLable

.getText())));

northPanel.add(fractionLable);

mainFrame.setBounds(280, 100, 500, 450);

mainFrame.setVisible(true);

}

 

public void randomBuild() {

int randoms, cols, rows;

for (int twins = 1; twins <= 15; twins++) {

randoms = (int) (Math.random() * 25 + 1);

for (int alike = 1; alike <= 2; alike++) {

cols = (int) (Math.random() * 6 + 1);

rows = (int) (Math.random() * 5 + 1);

while (grid[cols][rows] != 0) {

cols = (int) (Math.random() * 6 + 1);

rows = (int) (Math.random() * 5 + 1);

}

this.grid[cols][rows] = randoms;

}

}

}

 

public void fraction() {

fractionLable.setText(String.valueOf(Integer.parseInt(fractionLable

.getText()) + 100));

}

 

public void reload() {

int save[] = new int[30];

int n = 0, cols, rows;

int grid[][] = new int[8][7];

for (int i = 0; i <= 6; i++) {

for (int j = 0; j <= 5; j++) {

if (this.grid[i][j] != 0) {

save[n] = this.grid[i][j];

n++;

}

}

}

n = n - 1;

this.grid = grid;

while (n >= 0) {

cols = (int) (Math.random() * 6 + 1);

rows = (int) (Math.random() * 5 + 1);

while (grid[cols][rows] != 0) {

cols = (int) (Math.random() * 6 + 1);

rows = (int) (Math.random() * 5 + 1);

}

this.grid[cols][rows] = save[n];

n--;

}

mainFrame.setVisible(false);

pressInformation = false; // ����һ��Ҫ����ť�����Ϣ��Ϊ��ʼ

init();

for (int i = 0; i < 6; i++) {

for (int j = 0; j < 5; j++) {

if (grid[i + 1][j + 1] == 0)

diamondsButton[i][j].setVisible(false);

}

}

}

 

public void estimateEven(int placeX, int placeY, JButton bz) {

if (pressInformation == false) {

x = placeX;

y = placeY;

secondMsg = grid[x][y];

secondButton = bz;

pressInformation = true;

} else {

x0 = x;

y0 = y;

firstMsg = secondMsg;

firstButton = secondButton;

x = placeX;

y = placeY;

secondMsg = grid[x][y];

secondButton = bz;

if (firstMsg == secondMsg && secondButton != firstButton) {

xiao();

}

}

}

 

public void xiao() { // ��ͬ��������ܲ�����ȥ����ϸ��������һ����ע��

if ((x0 == x && (y0 == y + 1 || y0 == y - 1))

|| ((x0 == x + 1 || x0 == x - 1) && (y0 == y))) { // �ж��Ƿ�����

remove();

} else {

for (j = 0; j < 7; j++) {

if (grid[x0][j] == 0) { // �жϵ�һ����ťͬ���ĸ���ťΪ��

if (y > j) { // ����ڶ�����ť��Y������ڿհ�ť��Y����˵����һ��ť�ڵڶ���ť���

for (i = y - 1; i >= j; i--) { // �жϵڶ���ť���ֱ����һ��ť�м���û�а�ť

if (grid[x][i] != 0) {

k = 0;

break;

} else {

k = 1;

} // K=1˵��ͨ���˵�һ����֤

}

if (k == 1) {

linePassOne();

}

}

if (y < j) { // ����ڶ�����ť��Y����С�ڿհ�ť��Y����˵����һ��ť�ڵڶ���ť�ұ�

for (i = y + 1; i <= j; i++) { // �жϵڶ���ť���ֱ����һ��ť�м���û�а�ť

if (grid[x][i] != 0) {

k = 0;

break;

} else {

k = 1;

}

}

if (k == 1) {

linePassOne();

}

}

if (y == j) {

linePassOne();

}

}

if (k == 2) {

if (x0 == x) {

remove();

}

if (x0 < x) {

for (n = x0; n <= x - 1; n++) {

if (grid[n][j] != 0) {

k = 0;

break;

}

if (grid[n][j] == 0 && n == x - 1) {

remove();

}

}

}

if (x0 > x) {

for (n = x0; n >= x + 1; n--) {

if (grid[n][j] != 0) {

k = 0;

break;

}

if (grid[n][j] == 0 && n == x + 1) {

remove();

}

}

}

}

}

for (i = 0; i < 8; i++) { // ��

if (grid[i][y0] == 0) {

if (x > i) {

for (j = x - 1; j >= i; j--) {

if (grid[j][y] != 0) {

k = 0;

break;

} else {

k = 1;

}

}

if (k == 1) {

rowPassOne();

}

}

if (x < i) {

for (j = x + 1; j <= i; j++) {

if (grid[j][y] != 0) {

k = 0;

break;

} else {

k = 1;

}

}

if (k == 1) {

rowPassOne();

}

}

if (x == i) {

rowPassOne();

}

}

if (k == 2) {

if (y0 == y) {

remove();

}

if (y0 < y) {

for (n = y0; n <= y - 1; n++) {

if (grid[i][n] != 0) {

k = 0;

break;

}

if (grid[i][n] == 0 && n == y - 1) {

remove();

}

}

}

if (y0 > y) {

for (n = y0; n >= y + 1; n--) {

if (grid[i][n] != 0) {

k = 0;

break;

}

if (grid[i][n] == 0 && n == y + 1) {

remove();

}

}

}

}

}

}

}

 

public void linePassOne() {

if (y0 > j) { // ��һ��ťͬ�пհ�ť�����

for (i = y0 - 1; i >= j; i--) { // �жϵ�һ��ťͬ���հ�ť֮����û��ť

if (grid[x0][i] != 0) {

k = 0;

break;

} else {

k = 2;

} // K=2˵��ͨ���˵ڶ�����֤

}

}

if (y0 < j) { // ��һ��ťͬ�пհ�ť����ڶ���ť֮��

for (i = y0 + 1; i <= j; i++) {

if (grid[x0][i] != 0) {

k = 0;

break;

} else {

k = 2;

}

}

}

}

 

public void rowPassOne() {

if (x0 > i) {

for (j = x0 - 1; j >= i; j--) {

if (grid[j][y0] != 0) {

k = 0;

break;

} else {

k = 2;

}

}

}

if (x0 < i) {

for (j = x0 + 1; j <= i; j++) {

if (grid[j][y0] != 0) {

k = 0;

break;

} else {

k = 2;

}

}

}

}

 

public void remove() {

firstButton.setVisible(false);

secondButton.setVisible(false);

fraction();

pressInformation = false;

k = 0;

grid[x0][y0] = 0;

grid[x][y] = 0;

}

 

public void actionPerformed(ActionEvent e) {

if (e.getSource() == newlyButton) {

int grid[][] = new int[8][7];

this.grid = grid;

randomBuild();

mainFrame.setVisible(false);

pressInformation = false;

init();

}

if (e.getSource() == exitButton)

System.exit(0);

if (e.getSource() == resetButton)

reload();

for (int cols = 0; cols < 6; cols++) {

for (int rows = 0; rows < 5; rows++) {

if (e.getSource() == diamondsButton[cols][rows])

estimateEven(cols + 1, rows + 1, diamondsButton[cols][rows]);

}

}

}

 

public static void main(String[] args) {

LinkGame llk = new LinkGame();

llk.randomBuild();

llk.init();

}

}
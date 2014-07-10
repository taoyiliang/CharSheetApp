#include "charwindow.h"
#include "ui_charwindow.h"

CharWindow::CharWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::CharWindow)
{
    ui->setupUi(this);
}

CharWindow::~CharWindow()
{
    delete ui;
}

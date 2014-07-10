#include "toolwindow.h"
#include "ui_toolwindow.h"

toolwindow::toolwindow(QWidget *parent) :
    QTabWidget(parent),
    ui(new Ui::toolwindow)
{
    ui->setupUi(this);
}

toolwindow::~toolwindow()
{
    delete ui;
}

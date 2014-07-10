/********************************************************************************
** Form generated from reading UI file 'charwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.3.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_CHARWINDOW_H
#define UI_CHARWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_CharWindow
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *CharWindow)
    {
        if (CharWindow->objectName().isEmpty())
            CharWindow->setObjectName(QStringLiteral("CharWindow"));
        CharWindow->resize(400, 300);
        menuBar = new QMenuBar(CharWindow);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        CharWindow->setMenuBar(menuBar);
        mainToolBar = new QToolBar(CharWindow);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        CharWindow->addToolBar(mainToolBar);
        centralWidget = new QWidget(CharWindow);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        CharWindow->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(CharWindow);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        CharWindow->setStatusBar(statusBar);

        retranslateUi(CharWindow);

        QMetaObject::connectSlotsByName(CharWindow);
    } // setupUi

    void retranslateUi(QMainWindow *CharWindow)
    {
        CharWindow->setWindowTitle(QApplication::translate("CharWindow", "CharWindow", 0));
    } // retranslateUi

};

namespace Ui {
    class CharWindow: public Ui_CharWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_CHARWINDOW_H

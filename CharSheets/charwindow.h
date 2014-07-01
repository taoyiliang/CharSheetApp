#ifndef CHARWINDOW_H
#define CHARWINDOW_H

#include <QMainWindow>

namespace Ui {
class CharWindow;
}

class CharWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit CharWindow(QWidget *parent = 0);
    ~CharWindow();

private:
    Ui::CharWindow *ui;
};

#endif // CHARWINDOW_H

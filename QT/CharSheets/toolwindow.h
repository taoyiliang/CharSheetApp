#ifndef TOOLWINDOW_H
#define TOOLWINDOW_H

#include <QTabWidget>

namespace Ui {
class toolwindow;
}

class toolwindow : public QTabWidget
{
    Q_OBJECT

public:
    explicit toolwindow(QWidget *parent = 0);
    ~toolwindow();

private:
    Ui::toolwindow *ui;
};

#endif // TOOLWINDOW_H

import sys
from PyQt5.QtWidgets import QApplication, QWidget, QToolTip, QPushButton,\
                            QMessageBox, QMainWindow, QAction, qApp, QLabel,\
                            QHBoxLayout, QVBoxLayout
from PyQt5.QtGui import QIcon, QFont
from PyQt5.QtCore import QCoreApplication


class Example(QWidget):

    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):

        QToolTip.setFont(QFont('SansSerif', 14))
        self.setToolTip('This is a <b>QWidget</b> widget')
        btn = QPushButton('按钮', self)
        btn.setToolTip('This is a <b>QPushButton</b> widget')
        btn.resize(btn.sizeHint())
        btn.move(50, 50)

        # Quit Button
        qbtn = QPushButton('退出', self)
        qbtn.clicked.connect(QCoreApplication.instance().quit)
        qbtn.resize(qbtn.sizeHint())
        qbtn.move(200, 50)

        self.resize(500, 400)
        self.move(400, 400)
        self.setWindowTitle('Icon')
        self.setWindowIcon(QIcon('web.png'))

        self.show()

    # Message Box
    def closeEvent(self, event):

        reply = QMessageBox.question(self, 'Message', "Are you sure to quit ?",
                                     QMessageBox.Yes | QMessageBox.No, 
                                     QMessageBox.No)
        if reply == QMessageBox.Yes:
            event.accept()
        else:
            event.ignore()


class StatusBar(QMainWindow):

    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        # Menu Bar
        exitAction = QAction(QIcon('exit.png'), '&Exit', self)
        exitAction.setShortcut('Ctrl+Q')
        exitAction.setStatusTip('Exit Application')
        exitAction.triggered.connect(qApp.quit)

        self.statusBar()

        menubar = self.menuBar()
        fileMenu = menubar.addMenu('&File')
        fileMenu.addAction(exitAction)

        # Status Bar
        self.statusBar().showMessage('Ready')

        self.setGeometry(300, 300, 300, 200)
        self.setWindowTitle('Statusbar')
        self.show()


# 箱布局
class LayoutEx(QWidget):

    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        okButton = QPushButton("OK")
        cancelButton = QPushButton("Cancel")

        hbox = QHBoxLayout()
        hbox.addStretch(1)
        hbox.addWidget(okButton)
        hbox.addWidget(cancelButton)

        vbox = QVBoxLayout()
        vbox.addStretch(1)
        vbox.addLayout(hbox)

        self.setLayout(vbox)

        self.setGeometry(500, 500, 500, 300)
        self.setWindowTitle("Buttons")
        self.show()


# 网格布局

if __name__ == '__main__':

    app = QApplication(sys.argv)
    exam = LayoutEx()

    sys.exit(app.exec_())

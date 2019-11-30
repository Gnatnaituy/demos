import os

from werkzeug.wsgi import DispatcherMiddleware
from flask_migrate import Migrate, MigrateCommand
from flask_script import Manager, Shell
from app_blog import create_blog
from app_demo import create_demo


blog = create_blog(os.getenv('FLASK_CONFIG') or 'default')
demo = create_demo(os.getenv('FLASK_CONFIG') or 'default')
app = DispatcherMiddleware(demo, {
    '/app_blog': blog
})

manager = Manager(app)
migrate = Migrate(app)


def make_shell_context():       # shell with import app_blog, db
    return dict(app=app)


# py manager.py shell
manager.add_command('shell', Shell(make_context=make_shell_context))
manager.add_command('db', MigrateCommand)       # Database Migrate


if __name__ == '__main__':
    manager.run()

import os


class Config:
    SECRET_KEY = os.environ.get('SECRET_KEY') or 'nsygdsb'
    FLASKY_ADMIN = os.environ.get('FLASKY_ADMIN')
    # Database Configuration
    SQLALCHEMY_TRACK_MODIFICATIONS = True
    SQLALCHEMY_COMMIT_ON_TEARDOWN = True
    MYSQL_USERNAME = os.environ.get('MYSQL_USERNAME')
    MYSQL_PASSWORD = os.environ.get('MYSQL_PASSWORD')
    # Mail Configuration
    MAIL_SERVER = 'smtp.qq.com'
    MAIL_PORT = 465
    MAIL_USE_TLS = False
    MAIL_USE_SSL = True
    MAIL_USERNAME = os.environ.get('MAIL_USERNAME')
    MAIL_PASSWORD = os.environ.get('MAIL_PASSWORD')
    FLASKY_MAIL_SUBJECT_PREFIX = '[FLASKY]'
    FLASKY_MAIL_SENDER = '1758267482@qq.com'

    @staticmethod
    def init_app(app):
        pass


class DevelopmentConfig(Config):
    DEBUG = True
    SQLALCHEMY_DATABASE_URI = 'mysql://' + str(os.environ.get('MYSQL_USERNAME')) \
                              + ':' + str(os.environ.get('MYSQL_PASSWORD')) + '@localhost/flasky_dev'


class TestConfig(Config):
    TESTING = True
    SQLALCHEMY_DATABASE_URI = 'mysql://' + str(os.environ.get('MYSQL_USERNAME')) \
                              + ':' + str(os.environ.get('MYSQL_PASSWORD')) + '@localhost/flasky_test'


class ProductionConfig(Config):
    TESTING = False
    SQLALCHEMY_DATABASE_URI = 'mysql://' + str(os.environ.get('MYSQL_USERNAME')) \
                              + ':' + str(os.environ.get('MYSQL_PASSWORD')) + '@localhost/flasky'


config = {
    'default': DevelopmentConfig,
    'devolopment': DevelopmentConfig,
    'test': TestConfig,
    'production': ProductionConfig
}

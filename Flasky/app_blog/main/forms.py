from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField, TextAreaField
from wtforms.validators import DataRequired, Length


class ArticleForm(FlaskForm):
    title = StringField('Title', validators=[DataRequired('Title Required'), Length(0, 128)])
    body = TextAreaField('Body', validators=[DataRequired('Empty Body')])
    submit = SubmitField('Submit')

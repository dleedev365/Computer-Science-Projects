from django import forms

from .models import Post
from django.contrib.admin import widgets
from django.forms import ModelForm


# Create the form class
class Form(ModelForm):
    class Meta:
        model = Post
        fields = ['title','username', 'body', 'created_at']

    # def __init__(self, *args, **kwargs):
    #     super(Form, self).__init__(*args, **kwargs)
    #     instance = getattr(self, 'instance', None)
    #     if instance and instance.pk:
    #         self.fields['username'].widget.attrs['readonly'] = True
    #
    # # Ensure that the readonly value won't be overriden by a POST
    # def clean_username(self):
    #     instance = getattr(self, 'instance', None)
    #     if instance and instance.pk:
    #         return instance.username
    #     else:
    #         return self.cleaned_data['username']

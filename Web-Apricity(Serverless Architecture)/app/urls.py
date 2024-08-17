from django.contrib import admin
from django.urls import path, include


urlpatterns = [
    # load posts to get the lastest posts first
    path('', include('home.urls')),
    path('admin/', admin.site.urls),
    path('events/', include('events.urls')),
    path('chat/', include('chat.urls')),
    path('posts/', include('posts.urls')),
]

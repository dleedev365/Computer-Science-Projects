from django.contrib import admin

from .models import Event

admin.site.site_header = "Event Admin"
admin.site.site_title = "Event Admin Area"
admin.site.index_title = "Welcome to the Event Admin page"

class EventAdmin(admin.ModelAdmin):
    fieldsets = [
        ('Event name', {'fields': ['event_name']}),
        ('Event description', {'fields': ['event_description']}),
        ('Event location', {'fields': ['event_location']}),
        ('Date of event', {'fields': ['event_date']}),
    ]


admin.site.register(Event, EventAdmin)

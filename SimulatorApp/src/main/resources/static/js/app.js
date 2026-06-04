(function() {
    'use strict';

    const STORAGE_KEY = 'simulador-theme';

    function getPreferredTheme() {
        const stored = localStorage.getItem(STORAGE_KEY);
        if (stored) return stored;
        return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light';
    }

    function setTheme(theme) {
        document.documentElement.setAttribute('data-theme', theme);
        localStorage.setItem(STORAGE_KEY, theme);
        const btn = document.getElementById('themeToggle');
        if (btn) {
            btn.innerHTML = theme === 'dark'
                ? '<i class="bi bi-sun-fill"></i>'
                : '<i class="bi bi-moon-fill"></i>';
            btn.setAttribute('title', theme === 'dark' ? 'Modo claro' : 'Modo oscuro');
        }
    }

    function toggleTheme() {
        const current = document.documentElement.getAttribute('data-theme') || 'light';
        setTheme(current === 'dark' ? 'light' : 'dark');
    }

    document.addEventListener('DOMContentLoaded', function() {
        setTheme(getPreferredTheme());
        const btn = document.getElementById('themeToggle');
        if (btn) {
            btn.addEventListener('click', toggleTheme);
        }

        // Fade in animation for main content
        const content = document.querySelector('.container > *:first-child');
        if (content) {
            content.classList.add('fade-in');
        }

        // Confirm dialogs for delete forms
        document.querySelectorAll('form[data-confirm]').forEach(function(form) {
            form.addEventListener('submit', function(e) {
                if (!confirm(form.getAttribute('data-confirm') || '¿Estás seguro?')) {
                    e.preventDefault();
                }
            });
        });

        // Auto-hide alerts after 5s
        document.querySelectorAll('.alert-auto-hide').forEach(function(alert) {
            setTimeout(function() {
                alert.style.transition = 'opacity 0.5s';
                alert.style.opacity = '0';
                setTimeout(function() { alert.remove(); }, 500);
            }, 5000);
        });

        // Table row click to detail link
        document.querySelectorAll('tr[data-href]').forEach(function(row) {
            row.style.cursor = 'pointer';
            row.addEventListener('click', function() {
                window.location.href = row.getAttribute('data-href');
            });
        });
    });
})();

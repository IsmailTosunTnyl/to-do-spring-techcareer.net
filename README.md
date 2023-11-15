# To-Do List Uygulaması (Backend) 📑

Bu To-Do List uygulamasının backend tarafı, Spring framework kullanılarak geliştirilmiştir. Frontend tarafı için [bu linkteki reposu](link-to-frontend-repo) ziyaret edebilirsiniz.

## Özellikler

- ✅ **Todo Ekleme:** Yeni görevleri eklemek için `POST /api/todo` endpoint'ini kullanabilirsiniz.
- ❌ **Todo Silme:** Tamamlanan görevleri silmek için `DELETE /api/todo/{id}` endpoint'ini kullanabilirsiniz.
- 🖊️ **Todo Düzenleme:** Var olan görevleri düzenlemek için `PUT /api/todo/{id}` endpoint'ini kullanabilirsiniz.
- 🗑️ **Toplu Silme:** Birden fazla görevi aynı anda silmek için `DELETE /api/todo` endpoint'ini kullanabilirsiniz.
- 🗑️ **Tamamlanmışları Toplu Silme:** Tamamlanan birden fazla görevi aynı anda silmek için `DELETE /api/todo/done` endpoint'ini kullanabilirsiniz.

## Swagger UI Erişimi

API dokümantasyonu için Swagger UI'ı kullanabilirsiniz. Swagger UI'a erişmek için [bu linki](http://ismailtosun.net:4015/swagger) kullanabilirsiniz.

## Live Demo

Canlı demo için [bu linki](http://ismailtosun.net:4015/) kullanabilirsiniz.

## Teknolojiler

- **Backend:** Spring framework kullanılmıştır.
- **Backend:** MySQL Database olarak kullanılmıştır.
- **Backend:** Docker Teknolojileri kullanılarak container haline getirilmiştir.
- **Backend:** Github Actions ile otomatik testlerin çalıştırılması ve deploy işlemi yapılmıştır.

## Kurulum

### Docker Kullanarak

1. Docker Container dosyasını [buraya](ismailtosun/springtodo-techcareer:lastest) tıklayarak indirin ya da
2. Bu komutu çalıştırın ve istediğiniz port'u belirtin:
   ```bash
   docker run -p 3000:3000 ismailtosun/springtodo-techcareer:lastest
   ```

### Proje Dosyalarını Kullanarak

1. Proje dosyalarını bilgisayarınıza kopyalayın.
2. Gerekli bağımlılıkları yüklemek için: `mvn install`
3. Uygulamayı başlatmak için: `mvn spring-boot:run`

## Kullanım

Backend uygulaması varsayılan olarak `http://localhost:8082` üzerinde çalışır.

Teşekkür ederiz! 🚀
